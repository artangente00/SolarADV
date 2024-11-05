package com.PlayGroundAdv.Solar.service.sheets;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.TldSubNamesEntity;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentTLDRequest;
import com.PlayGroundAdv.Solar.model.libraries.TLDSubNamesDto;
import com.PlayGroundAdv.Solar.repositories.sheets.TLDsubNamesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;

@Service
public class TLDSubNamesServices {

	final TLDsubNamesRepository subNameRepository;
	final AuthenticationRepository userRepo;

	public TLDSubNamesServices(TLDsubNamesRepository subNameRepository, AuthenticationRepository userRepo) {
		this.subNameRepository = subNameRepository;
		this.userRepo = userRepo;
	}

	private Page<TLDSubNamesDto> convertDto(Page<TldSubNamesEntity> page, Pageable pageable) {
		try {
			return new PageImpl<>(page.stream().map(c -> new TLDSubNamesDto(c.getId(), c.getSubName(), c.getComponent(),
					c.getDescription(), c.getLastUpdate(), false,
					c.getAddedBy() != null ? c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName() : "",
					c.getUpdatedBy() != null ? c.getUpdatedBy().getFirstName() + " " + c.getUpdatedBy().getLastName()
							: "",
					c.getDeletedOn(),
					c.getDeletedBy() != null ? c.getDeletedBy().getFirstName() + " " + c.getDeletedBy().getLastName()
							: ""))
					.collect(Collectors.toList()), pageable, page.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<TLDSubNamesDto> getAllSubNames(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("component"));
			Page<TldSubNamesEntity> page = subNameRepository.findByIsDeleted(false, pageable);
			return convertDto(page, pageable);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<TLDSubNamesDto> getDeletedSubNames(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("component"));
			Page<TldSubNamesEntity> page = subNameRepository.findByIsDeleted(true, pageable);
			return convertDto(page, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<TLDSubNamesDto> searchSubName(ComponentTLDRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("component"));
			Page<TldSubNamesEntity> page = subNameRepository.findByIsDeletedAndSubNameIgnoreCaseContaining(false,
					request.getSubName().toUpperCase(), pageable);
			return convertDto(page, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<TLDSubNamesDto> searchDeleted(ComponentTLDRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("component"));
			Page<TldSubNamesEntity> page = subNameRepository.findByIsDeletedAndSubNameIgnoreCaseContaining(true,
					request.getSubName().toUpperCase(), pageable);
			return convertDto(page, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String addSubName(TldSubNamesEntity newSubName, Long idUser) {
		try {
			TldSubNamesEntity subName = subNameRepository.findBySubNameAndIsDeleted(newSubName.getSubName(), false);
			if (subName == null) {
				AuthentificationEntity user = this.userRepo.findById(idUser).orElse(new AuthentificationEntity());
				newSubName.setLastUpdate(new Date());
				newSubName.setAddedBy(user);
				newSubName.setIsDeleted(false);
				subNameRepository.save(newSubName);
				return "success";
			} else {
				return "exist";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String deleteSubName(Long idTLD, Long idUser) {
		try {
			AuthentificationEntity user = this.userRepo.findById(idUser).orElse(null);
			TldSubNamesEntity row = subNameRepository.findById(idTLD).orElse(null);
			if (user != null && row != null) {
				row.setIsDeleted(true);
				row.setDeletedOn(new Date());
				row.setDeletedBy(user);
				subNameRepository.save(row);
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String restore(Long idTLD) {
		try {
			TldSubNamesEntity subname = subNameRepository.findById(idTLD).orElse(null);
			if (subname != null) {
				Boolean exist = subNameRepository.existsBySubNameAndIsDeletedAndIdNot(subname.getSubName(), false,
						subname.getId());
				if (Boolean.FALSE.equals(exist)) {
					subname.setIsDeleted(false);
					subNameRepository.save(subname);
					return "success";
				} else {
					return "exist";
				}
			} else {
				return "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editSubName(TldSubNamesEntity editSubName, Long idUser) {
		try {

			Boolean exist = subNameRepository.existsBySubNameAndIsDeletedAndIdNot(editSubName.getSubName(), false,
					editSubName.getId());
			if (Boolean.FALSE.equals(exist)) {
				TldSubNamesEntity subname = subNameRepository.findById(editSubName.getId()).orElse(null);
				if (subname != null) {
					subname.setSubName(editSubName.getSubName());
					subname.setComponent(editSubName.getComponent());
					subname.setDescription(editSubName.getDescription());
					subname.setLastUpdate(new Date());
					subname.setUpdatedBy(userRepo.findById(idUser).orElse(null));
					subNameRepository.save(subname);
					return "success";
				}
				return "fail";
			} else {
				return "exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

}
