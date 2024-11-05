package com.PlayGroundAdv.Solar.service.equipment_utils;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class GetInverterOCPD {
	
	final InverterRepository inverterRepo;
	final CheckValueTypesService checkValueTypes;
	
	public GetInverterOCPD(InverterRepository inverterRepo, CheckValueTypesService checkValueTypes) {
		super();
		this.inverterRepo = inverterRepo;
		this.checkValueTypes = checkValueTypes;
	}

	public double getOcpdNumber(Long firstInverterId, Long secondInverterId, Long thirdInverterId,
			Long fourthInverterId, Long fifthInverterId) {
		try {

			double iacmaxSum = 0;
			if (firstInverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(firstInverterId);
				iacmaxSum = iacmax != null
						? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
						: 0;
			}
			if (secondInverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(secondInverterId);
				iacmaxSum = iacmaxSum
						+ (iacmax != null ? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
								: 0);
			}
			if (thirdInverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(thirdInverterId);
				iacmaxSum = iacmaxSum
						+ (iacmax != null ? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
								: 0);
			}
			if (fourthInverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(fourthInverterId);
				iacmaxSum = iacmaxSum
						+ (iacmax != null ? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
								: 0);
			}
			if (fifthInverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(fifthInverterId);
				iacmaxSum = iacmaxSum
						+ (iacmax != null ? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
								: 0);
			}

			return iacmaxSum * 1.25;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double getOcpdNumberMicroInverterS(Long microInverterID) {

		try {
			double iacmaxSum = 0;
			if (microInverterID != null) {
				String iacmax = inverterRepo.getInverterIacmax(microInverterID);
				iacmaxSum = iacmax != null
						? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
						: 0;
			}
			return iacmaxSum * 1.25;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double getInverterOcpdNumber(Long inverterId) {
		try {
			double InverterOcpdNumber = 0;
			if (inverterId != null) {
				String iacmax = inverterRepo.getInverterIacmax(inverterId);
				InverterOcpdNumber = checkValueTypes.NotEquals(iacmax, "")
						? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
						: 0;
			}
			return InverterOcpdNumber * 1.25;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
