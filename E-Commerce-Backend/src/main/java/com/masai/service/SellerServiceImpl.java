package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.SellerException;
import com.masai.models.Seller;
import com.masai.models.SellerDTO;
import com.masai.models.SessionDTO;
import com.masai.models.UserSession;
import com.masai.repository.SellerDao;
import com.masai.repository.SessionDao;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private LoginLogoutService loginService;
	
	@Autowired
	private SessionDao sessionDao;
	

	@Override
	public Seller addSeller(Seller seller) {
		
		Seller add= sellerDao.save(seller);
		
		return add;
	}

	@Override
	public List<Seller> getAllSellers() throws SellerException {
		
		List<Seller> sellers= sellerDao.findAll();
		
		if(sellers.size()>0) {
			return sellers;
		}
		else throw new SellerException("No Seller Found !");
		
	}

	@Override
	public Seller getSellerById(Integer sellerId) {
		
		Optional<Seller> seller=sellerDao.findById(sellerId);
		
		if(seller.isPresent()) {
			return seller.get();
		}
		else throw new SellerException("Seller not found for this ID: "+sellerId);
	}

	@Override
	public Seller updateSeller(Seller seller, String token) {
		
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		
		loginService.checkTokenStatus(token);
		
		Seller existingSeller=sellerDao.findById(seller.getSellerId()).orElseThrow(()-> new SellerException("Seller not found for this Id: "+seller.getSellerId()));
		Seller newSeller= sellerDao.save(seller);
		return newSeller;
	}

	@Override
	public Seller deleteSellerById(Integer sellerId, String token) {
		
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		
		loginService.checkTokenStatus(token);
		
		Optional<Seller> opt=sellerDao.findById(sellerId);
		
		if(opt.isPresent()) {
			
			UserSession user = sessionDao.findByToken(token).get();
			
			Seller existingseller=opt.get();
			
			if(user.getUserId() == existingseller.getSellerId()) {
				sellerDao.delete(existingseller);
				
				// logic to log out a seller after he deletes his account
				SessionDTO session = new SessionDTO();
				session.setToken(token);
				loginService.logoutSeller(session);
				
				return existingseller;
			}
			else {
				throw new SellerException("Verification Error in deleting seller account");
			}
		}
		else throw new SellerException("Seller not found for this ID: "+sellerId);
		
	}

	@Override
	public Seller updateSellerMobile(SellerDTO sellerdto, String token) throws SellerException {
		
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		
		loginService.checkTokenStatus(token);
		
		UserSession user = sessionDao.findByToken(token).get();
		
		Seller existingSeller=sellerDao.findById(user.getUserId()).orElseThrow(()->new SellerException("Seller not found for this ID: "+ user.getUserId()));
		
		if(existingSeller.getPassword().equals(sellerdto.getPassword())) {
			existingSeller.setMobile(sellerdto.getMobile());
			return sellerDao.save(existingSeller);
		}
		else {
			throw new SellerException("Error occured in updating mobile. Please enter correct password");
		}
		
	}

	@Override
	public Seller getSellerByMobile(String mobile, String token) throws SellerException {
		
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		
		loginService.checkTokenStatus(token);
		
		Seller existingSeller = sellerDao.findByMobile(mobile).orElseThrow( () -> new SellerException("Seller not found with given mobile"));
		
		return existingSeller;
	}
	
	@Override
	public Seller getCurrentlyLoggedInSeller(String token) throws SellerException{
		
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		
		loginService.checkTokenStatus(token);
		
		UserSession user = sessionDao.findByToken(token).get();
		
		Seller existingSeller=sellerDao.findById(user.getUserId()).orElseThrow(()->new SellerException("Seller not found for this ID"));
		
		return existingSeller;
		
	}
	
	
	// Method to update password - based on current token
	
	@Override
	public SessionDTO updateSellerPassword(SellerDTO sellerDTO, String token) {
				
		if(token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
			
			
		loginService.checkTokenStatus(token);
			
		UserSession user = sessionDao.findByToken(token).get();
			
		Optional<Seller> opt = sellerDao.findById(user.getUserId());
			
		if(opt.isEmpty())
			throw new SellerException("Seller does not exist");
			
		Seller existingSeller = opt.get();
			
			
		if(sellerDTO.getMobile().equals(existingSeller.getMobile()) == false) {
			throw new SellerException("Verification error. Mobile number does not match");
		}
			
		existingSeller.setPassword(sellerDTO.getPassword());
			
		sellerDao.save(existingSeller);
			
		SessionDTO session = new SessionDTO();
			
		session.setToken(token);
			
		loginService.logoutSeller(session);
			
		session.setMessage("Updated password and logged out. Login again with new password");
			
		return session;

	}

}
