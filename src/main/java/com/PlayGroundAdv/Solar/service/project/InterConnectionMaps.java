package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.PermitAdvEntity;
import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


@Service
@Transactional
public class InterConnectionMaps {
	
	final AuthenticationRepository authRepo;
	final ContractorInformationRepository contractorInfoRepo;
	final CheckValueTypesService checkValueTypesService;
	final PermitRepository permitRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitAdvInputsRepository permitAdvRepo;
	
	public InterConnectionMaps(AuthenticationRepository authRepo, ContractorInformationRepository contractorInfoRepo,
			CheckValueTypesService checkValueTypesService, PermitRepository permitRepo,
			PermitCompanyInfoRepository permitCompanyInfoRepo,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo,
			PermitAdvInputsRepository permitAdvRepo) {
		super();
		this.authRepo = authRepo;
		this.contractorInfoRepo = contractorInfoRepo;
		this.checkValueTypesService = checkValueTypesService;
		this.permitRepo = permitRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.permitAdvRepo = permitAdvRepo;
	}
	
	public HashMap<String,String> getValues(Long idPermit, Long ctdUser){
		HashMap<String,String> values = new HashMap<>();
		PermitEntity permitEntity = new PermitEntity();
		try {
			 permitEntity = permitRepo.findById(idPermit).orElseThrow(
						() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			
		} catch (Exception e) {
			permitEntity = null;
		}
		PermitCompanyInfoEntity companyInfo = null;
		AuthentificationEntity user = null;
		AuthentificationEntity contractor = null;
		PermitHomeSiteInfoEntity homeSiteInfo = null;
		PermitAdvEntity advEntity = null;
		try{
			companyInfo = permitCompanyInfoRepo.findOneByPermitEntityId(idPermit);
			homeSiteInfo = permitHomeSiteInfoRepo.findOneByPermitEntityId(idPermit);
			advEntity = permitAdvRepo.findOneByPermitEntityId(idPermit);
			Long idUser = permitEntity.getAuthentificationEntity().getId();
			
			ContractorInformationEntity userInfo = contractorInfoRepo.findByAuthentificationEntityId(ctdUser);
			ContractorInformationEntity contractorInfo = contractorInfoRepo.findByAuthentificationEntityId(idUser);
			
			user = authRepo.findById(ctdUser)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + ctdUser));
			contractor = authRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" +idUser));
			
			if(checkValueTypesService.Equals(companyInfo.getSnemApplication(),"yes"))
				values.put("agreement_type", "S");
			else if(companyInfo.getApplicationType()!=null)
				values.put("agreement_type", "M");
			if(contractor.getCompany()!=null)
				values.put("last_name", contractor.getCompany());
			else
				values.put("last_name", contractor.getFirstName()+" "+ contractor.getLastName());
				values.put("contractor_company_name", permitEntity.getHomeOwnName());	
			values.put("badge_number",homeSiteInfo.getMeterNumber() == null ? "":homeSiteInfo.getMeterNumber() );
			values.put("service_agreement_id", advEntity.getCustomersAccountNumber()==null ?"" :advEntity.getCustomersAccountNumber());
			values.put("ma_contact_name", user.getFirstName()+" "+user.getLastName());
			values.put("sp_zip", userInfo.getPostalCode());
			values.put("ma_contact_phone", userInfo.getContactPhone()==null ?"" :userInfo.getContactPhone());
			values.put("contractor_company_name", user.getCompany()==null ? "" :user.getCompany());
			values.put("contractor_last_name", user.getLastName());
			values.put("contractor_business_phone",userInfo.getContactPhone() ==null ?"":userInfo.getContactPhone());
			values.put("contractor_email", user.getEmail()==null ? "":user.getEmail());
			values.put("sp_address", homeSiteInfo.getSiteAddress());
			values.put("sp_city",homeSiteInfo.getCity());
			values.put("home_phone", contractorInfo.getContactPhone()==null ?"" :contractorInfo.getContactPhone());
			values.put("email", homeSiteInfo.getEmailPhone() ==null? contractor.getEmail() :homeSiteInfo.getEmailPhone());
			values.put("authorized_to_act", "On");
			values.put("system_type", "S");
			values.put("usage", companyInfo.getKwhUsage()==null ?"":companyInfo.getKwhUsage());
			values.put("plan_increase", companyInfo.getPlannedAnnual()==null ?"":companyInfo.getPlannedAnnual()+"" );
			if(checkValueTypesService.NotEquals(companyInfo.getElectriccvehichule1(),"*0")){
				values.put("ev_charging", "Y");
				values.put("ev_number", companyInfo.getElectriccvehichule1());
			}else
				values.put("ev_charging", "N");
			
			if(checkValueTypesService.Equals(companyInfo.getMeterDisco(),true))
				values.put("meter_in_building", "On");
			if(checkValueTypesService.Equals(companyInfo.getUndertrainedAnimal(),true))
				values.put("animal_at_meter", "On");
			if(checkValueTypesService.Equals(companyInfo.getOtherSystem(),true)){
				values.put("meter_access_other", "On");
				values.put("meter_other_text", companyInfo.getOtherSystemText());
			}
			if(checkValueTypesService.Equals(companyInfo.getTypeCustomer(),"Resedential"))
				values.put("customer_sector", "R");
			else if(checkValueTypesService.Equals(companyInfo.getTypeCustomer(),"OtherCustomer")){
				if(checkValueTypesService.Equals(companyInfo.getTypeCustomerOther(),"Commercial"))
						values.put("customer_sector", "C");
				else if(checkValueTypesService.Equals(companyInfo.getTypeCustomerOther(),"Industrial"))
						values.put("customer_sector", "I");
				else if( checkValueTypesService.Equals(companyInfo.getTypeCustomerOther(),"Non-Profit"))
						values.put("customer_sector", "NP");
				else if( checkValueTypesService.Equals(companyInfo.getTypeCustomerOther(),"Educational"))
						values.put("customer_sector", "E");
				else if( checkValueTypesService.Equals(companyInfo.getTypeCustomerOther(),"Other Government"))
						values.put("customer_sector", "OG");
			}
			if(companyInfo.getNewRate()!= null){
				values.put("rate_option", "A-1");
				values.put("consent_leaving_rate", "On");
				if(checkValueTypesService.Equals(companyInfo.getNewRate(),"E1")){
					values.put("requested_rate", "E-9");
					values.put("other_com_rate", "ETOU-A");
				}
				else if(checkValueTypesService.Equals(companyInfo.getNewRate(),"E6")){
					values.put("requested_rate", "E-6");
					values.put("other_com_rate", "ETOU-A");
				}
				else if(checkValueTypesService.Equals(companyInfo.getNewRate(),"E7")){
					values.put("requested_rate", "E-7");
					values.put("other_com_rate", "E1");
				}
				else if(checkValueTypesService.Equals(companyInfo.getNewRate(),"OtherNewRate")){
					values.put("requested_rate", "Other_Commercial");
					values.put("other_com_rate", companyInfo.getTextOtherNewRate()==null?"":companyInfo.getTextOtherNewRate());
				}
			}
			Date d = new Date();
			
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm");
			formattedDATE.setTimeZone(TimeZone.getTimeZone("Canada/Pacific"));
		    values.put("customer_name", contractor.getFirstName()+" "+contractor.getLastName());
			values.put("signature_date", formattedDATE.format(d));
			values.put("pv_cecac", "Missing Information");
			values.put("size", "Missing Information");
		}catch(Exception nre){
			nre.printStackTrace();
		}
		
		return values;
	}
	public void fill(Long idPermit, Long ctdUser){
			File fileRe = null;
		try {
			PdfReader.unethicalreading = true;
			PdfReader reader = new PdfReader( "C:/Users/nuatn-radh/Downloads/Form_79-1151A NEM2.pdf" );
			fileRe= new File( "C:/Users/nuatn-radh/Downloads/Sans titreeee.PDF" );
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			
			
			HashMap<String, String> values = getValues(idPermit,ctdUser);
			for(Map.Entry<String, String> entry : values.entrySet()){
				form.setField(entry.getKey(), entry.getValue());
			}
			for (Iterator i = form.getFields().keySet().iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                String[] states;
                switch(form.getFieldType(key)) {
                    case AcroFields.FIELD_TYPE_CHECKBOX:
                        states = form.getAppearanceStates(key);
                        break;
                    case AcroFields.FIELD_TYPE_COMBO:
                        states = form.getAppearanceStates(key);
                        break;
                    case AcroFields.FIELD_TYPE_LIST:
                        states = form.getAppearanceStates(key);
                        break;
                    case AcroFields.FIELD_TYPE_NONE:
                        break;
                    case AcroFields.FIELD_TYPE_PUSHBUTTON:
                        break;
                    case AcroFields.FIELD_TYPE_RADIOBUTTON:
                        states = form.getAppearanceStates(key);
                        break;
                    case AcroFields.FIELD_TYPE_SIGNATURE:
                        break;
                    case AcroFields.FIELD_TYPE_TEXT:
                        break;
                    default:
                }
            }
			stamper.close();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
