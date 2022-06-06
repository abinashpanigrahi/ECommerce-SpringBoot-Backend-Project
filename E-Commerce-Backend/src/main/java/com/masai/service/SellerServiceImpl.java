package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.SellerException;
import com.masai.models.Seller;
import com.masai.repository.SellerDao;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerDao sellerDao;

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
	public Seller updateSeller(Seller seller) {
		
		Seller existingSeller=sellerDao.findById(seller.getSellerId()).orElseThrow(()-> new SellerException("Seller not found for this Id: "+seller.getSellerId()));
	   Seller newSeller= sellerDao.save(seller);
		return newSeller;
	}

	@Override
	public Seller deleteSellerById(Integer sellerId) {
		Optional<Seller> opt=sellerDao.findById(sellerId);
		
		if(opt.isPresent()) {
			Seller existingseller=opt.get();
			sellerDao.delete(existingseller);
			return existingseller;
		}
		else throw new SellerException("Seller not found for this ID: "+sellerId);
		
	}

	@Override
	public Seller updateSellerMobile(Integer sellerId, String mobile) throws SellerException {
		
		Seller existingSeller=sellerDao.findById(sellerId).orElseThrow(()->new SellerException("Seller not found for this ID: "+sellerId));
		
		existingSeller.setMobile(mobile);
		
		return sellerDao.save(existingSeller);
	}
	
	

}
