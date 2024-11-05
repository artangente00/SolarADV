package com.PlayGroundAdv.Solar.service.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RFIQuestionEntity;
import com.PlayGroundAdv.Solar.entity.RFInformationEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.RFIQuestionFavRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.RFIInformationRepository;
import com.PlayGroundAdv.Solar.repositories.RFIQuestionRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class RFIQuestionService {


	final HistoryActivityService historicActivity;
	final CheckValueTypesService checkValueTypesService;
	final RFIQuestionRepository rfiQuestionEntityRepo;
	final AuthenticationRepository authentificationEntityRepo;
	final RFIInformationRepository rfiInformationRepository;
	public RFIQuestionService(HistoryActivityService historicActivity,
			CheckValueTypesService checkValueTypesService, RFIQuestionRepository rfiQuestionEntityRepo,
			AuthenticationRepository authentificationEntityRepo, RFIInformationRepository rfiInformationRepository) {
		super();
		this.historicActivity = historicActivity;
		this.checkValueTypesService = checkValueTypesService;
		this.rfiQuestionEntityRepo = rfiQuestionEntityRepo;
		this.rfiInformationRepository = rfiInformationRepository;
		this.authentificationEntityRepo = authentificationEntityRepo;
	}

	/*****************************
	 * Edit RFI Question
	 **************************************************/

	public String editRFIQuestion(RFIQuestionFavRequest question, Long idUser) {

		try {
			RFIQuestionEntity updaterfiQuestion = rfiQuestionEntityRepo.findById(question.getIdQuestion()).orElseThrow(
					() -> new ResourceNotFoundException("RFI Question not found for this id :" +question.getIdQuestion()));
			// Update selected Question when edit a question.
			
			List<RFInformationEntity> rFInformationEntitys =  rfiInformationRepository.findByAttributeNameAndSplitAdvQuestion(updaterfiQuestion.getFieldName(),updaterfiQuestion.getQuestionstatic() + " " + updaterfiQuestion.getRfiQuestion());
			if (rFInformationEntitys != null && !rFInformationEntitys.isEmpty()) {
				

				for (RFInformationEntity rFInformationEntity : rFInformationEntitys) {

					if(rFInformationEntity != null && rFInformationEntity.getAdvQuestion() != null) {
						rFInformationEntity.setAdvQuestion(rFInformationEntity.getAdvQuestion().split("::")[0] + "::"
							+ question.getQuestionstatic() + " " + question.getRfiQuestion() + "::");
					}
				}
			}

			updaterfiQuestion.setFieldName(question.getFieldName());
			updaterfiQuestion.setQuestionActived(question.isQuestionActived());
			updaterfiQuestion.setQuestionstatic(question.getQuestionstatic());
			updaterfiQuestion.setRfiQuestion(question.getRfiQuestion());
			updaterfiQuestion.setConfirmation(question.isConfirmation());
			rfiQuestionEntityRepo.save(updaterfiQuestion);
			historicActivity.recordActivity(
					idUser, "", "", "libraries;Edit component " + question.getFieldName() + " "
							+ question.getQuestionstatic() + " " + question.getRfiQuestion() + ".;RFI Library",
					true, "", "", "");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUser, "", "", "libraries;Edit component.;RFI Library", false, "", "", "");
			return "fail";
		}
	}
	
	/*****************************Update existing of the file in the RFI Question table***********************************/
	public String updateExistingOfDocument(Long idRFIQuestion) {
		
		
		try {
			
			
			if (idRFIQuestion != null) {
				RFIQuestionEntity rFIQuestionEntity=rfiQuestionEntityRepo.findById(idRFIQuestion).orElse(null);
				if(rFIQuestionEntity != null) {
					rFIQuestionEntity.setRFIDocument(true);
					rfiQuestionEntityRepo.save(rFIQuestionEntity);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "echec";
		}
		
		
		return "ok";
	}

	 /*************** Update RFI question **************************************/
	
	public String updateRFIQuestionFavorite(RFIQuestionFavRequest question, Long idUser) {

		try {
			AuthentificationEntity user = authentificationEntityRepo.findById(idUser).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUser));
			RFIQuestionEntity updaterfiQuestion = rfiQuestionEntityRepo.findById(question.getIdQuestion()).orElseThrow(
					() -> new ResourceNotFoundException("RFI Question not found for this id :" +question.getIdQuestion()));
			if (question.isQuestionActived()) {
				updaterfiQuestion.setQuestionActived(true);
				historicActivity.recordActivity(idUser, "", "",
						"libraries;The favorite " + question.getFieldName() + " " + question.getQuestionstatic() + " "
								+ question.getRfiQuestion() + " is added to the user " + user.getFirstName() + " "
								+ user.getLastName() + ".;RFI Library",
						true, "", "", "");
			} else {
				updaterfiQuestion.setQuestionActived(false);
				historicActivity.recordActivity(idUser, "", "",
						"libraries;The favorite " + question.getFieldName() + " " + question.getQuestionstatic() + " "
								+ question.getRfiQuestion() + " is removed from the user " + user.getFirstName() + " "
								+ user.getLastName() + ".;RFI Library",
						true, "", "", "");
			}
			rfiQuestionEntityRepo.save(updaterfiQuestion);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUser, "", "", "libraries;Update RFI.;RFI Library", false, "", "", "");
			return "fail";
		}
	}



	// /*******************************
	// * Method of search
	// ********************************/
	public List<String> getAllFieldName() {
		
		try {
			return rfiQuestionEntityRepo.findDistinctFieldName();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		
	}



	/******************************** Add Question ********************************/
	public String addRFIQuestion(RFIQuestionFavRequest question, Long idUser) {
		try {
			RFIQuestionEntity rFIQuestionEntity = new RFIQuestionEntity();
			rFIQuestionEntity.setFieldName(question.getFieldName());
			rFIQuestionEntity.setRfiQuestion(question.getRfiQuestion());
			rFIQuestionEntity.setQuestionstatic(question.getQuestionstatic());
			rFIQuestionEntity.setQuestionActived(question.isQuestionActived());
			rFIQuestionEntity.setConfirmation(question.isConfirmation());
			rFIQuestionEntity.setAddedBy("Super User");
			rfiQuestionEntityRepo.save(rFIQuestionEntity);
			 historicActivity.recordActivity(idUser, "", "",
						"libraries;Add component " + question.getFieldName() + " " + question.getQuestionstatic()+ " " +question.getRfiQuestion()+ ".;RFI Library"
								,
				true,"","","");
			return ("success");
		} catch (Exception e) {
			e.printStackTrace();
			 historicActivity.recordActivity(idUser, "", "",
						"libraries;Add component.;RFI Library"
								,
				false,"","","");
			return "error";

		}
	}

	
	
	public List<RFIQuestionFavRequest>  getAllQuestionSearchedLibraryTrue(String fieldName) {
		
		try {
			List<RFIQuestionFavRequest> allQuestion = new ArrayList<>();
			List<RFIQuestionEntity> listRfi  =rfiQuestionEntityRepo.findByFieldNameAndQuestionActived(fieldName, true);

					if (listRfi != null && !listRfi.isEmpty()) {
						for(int i=0; i<listRfi.size() ;i++) {
							allQuestion.add(new RFIQuestionFavRequest(listRfi.get(i).getId_RFIQuestion(), listRfi.get(i).getQuestionstatic(), listRfi.get(i).getFieldName(), listRfi.get(i).getRfiQuestion(),listRfi.get(i).isQuestionActived(),listRfi.get(i).getAddedBy(), listRfi.get(i).isConfirmation()));
						}
						
						return allQuestion;
					}else{
						return allQuestion;
					}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	public Page<RFIQuestionFavRequest> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
			String fieldNameClean = checkValueTypesService.isStringNotEmpty(request.getFieldName())
					? request.getFieldName()
					: null;
			if (fieldNameClean == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<RFIQuestionEntity> p = rfiQuestionEntityRepo.findAll(pageable);
				return convertDto(p, pageable);
			} else {
				Page<RFIQuestionEntity> p = rfiQuestionEntityRepo.findAll(filter(fieldNameClean, request.getIsFavorite()), pageable);
				return convertDto(p, pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<RFIQuestionFavRequest> convertDto(Page<RFIQuestionEntity> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new RFIQuestionFavRequest(c.getId_RFIQuestion(),c.getQuestionstatic(),
									  c.getFieldName(),c.getRfiQuestion(),c.isQuestionActived(),c.getAddedBy(),
									  c.isConfirmation())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<RFIQuestionEntity> filter(String fieldName, Boolean favorite) {

		return new Specification<RFIQuestionEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RFIQuestionEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicateFieldName = cb.equal(root.get("fieldName"), fieldName);
				Predicate endPredicateFieldName = fieldName != null
						? predicateFieldName
						: null;
				List<Predicate> list = Arrays.asList(endPredicateFieldName);
				
				if (Boolean.TRUE.equals(favorite)) {
					Predicate predicateFavorite = cb.equal(root.get("questionActived"), favorite);
					list = Arrays.asList(endPredicateFieldName, predicateFavorite);
				}			

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	
}
