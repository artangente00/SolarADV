package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetACDisconnectQty {

	final CheckValueTypesService  checkValue;
	
	public GetACDisconnectQty(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public int getStringAcDisconnectQty(Integer accAty, ACDisconnect acDisconnect ,ACDisconnect acDisconnectThree,ACDisconnect acDisconnectFour) {
		int qty = 0;
		try {
			
			qty = accAty != null && accAty > 0 ? accAty : 1;
			if (acDisconnectThree != null && acDisconnectThree.getId().equals(acDisconnect.getId())) {
				qty = qty +1;
			}
			if (acDisconnectFour != null && acDisconnectFour.getId().equals(acDisconnect.getId())) {
				qty = qty +1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}
	
	public int getStringAcDisconnectTwoQty(Integer accAty, ACDisconnect acDisconnect,ACDisconnect acDisconnectTwo,ACDisconnect acDisconnectThree,ACDisconnect acDisconnectFour) {
		int qty = 0;
		try {
			
			qty = accAty != null && accAty > 0 ? accAty : 1;
			if (acDisconnectThree != null && !acDisconnectThree.getId().equals( acDisconnect.getId()) && acDisconnectThree.getId().equals(acDisconnectTwo.getId())) {
				qty = qty +1;
				
			}
			if (acDisconnectFour != null && !acDisconnectFour.getId().equals( acDisconnect.getId()) && acDisconnectFour.getId().equals(acDisconnectTwo.getId())) {
				qty = qty +1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}


	
	
}
