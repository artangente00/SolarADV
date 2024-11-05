package com.PlayGroundAdv.Solar.service.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.project.ProjectsChart;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;

@Service
public class AnalyticService {

	final PermitRepository permitRepo;
	final AuthenticationRepository authRepo;

	public AnalyticService(PermitRepository permitRepo, AuthenticationRepository authRepo) {
		super();
		this.permitRepo = permitRepo;
		this.authRepo = authRepo;
	}

	/*
	 * getAllPermitForChart: the chart that displays in the login page
	 */
	public List<ProjectsChart> getCountProjectPerDay() {
		try {
			return permitRepo.findProjectCountPerDay();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<ProjectsChart> getCountProjectPerDayPerUser(Long idUser) {
		try {
			if (authRepo.findRole(idUser) == 2) {
				return permitRepo.findProjectCountPerDayPerUser(idUser);
			}
			return permitRepo.findProjectCountPerDay();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<List<String>> getAllPermitForChart() {
		List<List<String>> csvList = new ArrayList<List<String>>();

		try {
			Long nbRows = permitRepo.numberOfPermits();
			if (nbRows != null && nbRows != 0) {
				List<Date> permits = permitRepo.creationPermitDates();

				List<String> listYear = new ArrayList<String>();
				List<String> listMonth = new ArrayList<String>();
				List<String> listDay = new ArrayList<String>();
				List<Integer> listnumber = new ArrayList<Integer>();
				if (permits != null && permits.size() > 0) {
					String year = String.valueOf(permits.get(0).getYear() + 1900);
					String month = "";
					String day = "";

					if (Integer.parseInt(String.valueOf(permits.get(0).getMonth() + 1)) < 10)
						month = "0" + String.valueOf(permits.get(0).getMonth() + 1);
					else
						month = String.valueOf(permits.get(0).getMonth() + 1);
					if (Integer.parseInt(String.valueOf(permits.get(0).getDate())) < 10)
						day = "0" + String.valueOf(permits.get(0).getDate());
					else
						day = String.valueOf(permits.get(0).getDate());

					listYear.add(year);
					listMonth.add(month);
					listDay.add(day);
					listnumber.add(1);
					int numberLocation = 0;
					int dateLocation = 0;

					for (int i = 1; i < permits.size(); i++) {

						year = String.valueOf(permits.get(i).getYear() + 1900);
						if (Integer.parseInt(String.valueOf(permits.get(i).getMonth() + 1)) < 10) {
							month = "0" + String.valueOf(permits.get(i).getMonth() + 1);
						} else
							month = String.valueOf(permits.get(i).getMonth() + 1);
						if (Integer.parseInt(String.valueOf(permits.get(i).getDate())) < 10) {
							day = "0" + String.valueOf(permits.get(i).getDate());
						} else
							day = String.valueOf(permits.get(i).getDate());

						if (year.equalsIgnoreCase(listYear.get(dateLocation))) {

							if (month.equalsIgnoreCase(listMonth.get(dateLocation))) {

								if (day.equalsIgnoreCase(listDay.get(dateLocation))) {
									int qty = listnumber.get(numberLocation) + 1;
									listnumber.set(numberLocation, qty);
								} else {
									listYear.add(year);
									listMonth.add(month);
									listDay.add(day);
									listnumber.add(1);
									numberLocation++;
									dateLocation++;
								}

							} else {
								listYear.add(year);
								listMonth.add(month);
								listDay.add(day);
								listnumber.add(1);
								numberLocation++;
								dateLocation++;
							}
						} else {
							listYear.add(year);
							listMonth.add(month);
							listDay.add(day);
							listnumber.add(1);
							numberLocation++;
							dateLocation++;
						}
					}
				}
				int size = listnumber.size();
				listnumber.add(size);
				List<String> qty = new ArrayList<String>();
				for (int j = 0; j < listnumber.size(); j++) {
					qty.add(listnumber.get(j).toString());
				}
				csvList.add(listYear);
				csvList.add(listMonth);
				csvList.add(listDay);
				csvList.add(qty);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return csvList;
	}
}
