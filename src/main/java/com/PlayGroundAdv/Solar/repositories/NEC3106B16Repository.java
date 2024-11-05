package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.NEC_310_16_B_16;
import org.springframework.stereotype.Repository;

@Repository
public interface NEC3106B16Repository extends JpaRepository<NEC_310_16_B_16, Long> {

	List<NEC_310_16_B_16> findBytradeSze(String tradeSize);

	NEC_310_16_B_16 findFirstBytradeSze(String tradeSize);

	NEC_310_16_B_16 findFirstBytradeSzeAndNumberOfConductors(String tradeSize, Integer numberOfConductors);

//	A.B 05/24/2022 CR-953-MOD-001
	@Query(nativeQuery = true, value = "SELECT * from NEC_310_16_B_16 u where u.ninety_insulation >= :p90 ORDER BY number_of_conductors, ninety_insulation LIMIT 1")
	NEC_310_16_B_16 findFirstByNinetyInsulationGreaterThan(@Param("p90") Integer insulation);

	@Query(nativeQuery = true, value = "SELECT * from NEC_310_16_B_16 u where u.ninety_insulation >= :p90 and u.trade_sze NOT IN :notIn ORDER BY number_of_conductors, ninety_insulation LIMIT 1")
	NEC_310_16_B_16 findFirstByNinetyInsulationGreaterThanAndTradeSzeIsNotIn(@Param("p90") Integer insulation,
			@Param("notIn") List<String> incorrectTradeSize);

//	A.B 05/24/2022 CR-953-MOD-001
	@Query(nativeQuery = true, value = "SELECT * from NEC_310_16_B_16 u where u.seventy_five_insulation >= :p75 and u.trade_sze != :tradeSize and u.trade_sze NOT IN :notIn ORDER BY number_of_conductors, seventy_five_insulation LIMIT 1")
	NEC_310_16_B_16 findBySeventyFiveInsulationAndTradeSizeIsNotIn(@Param("p75") Integer insulation,
			@Param("tradeSize") String tradeSize, @Param("notIn") List<String> incorrectTradeSize);

	@Query(nativeQuery = true, value = "SELECT * from NEC_310_16_B_16 u where u.seventy_five_insulation >= :p75 and u.trade_sze != :tradeSize ORDER BY number_of_conductors, seventy_five_insulation LIMIT 1")
	NEC_310_16_B_16 findBySeventyFiveInsulationAndTradeSize(@Param("p75") Integer insulation,
			@Param("tradeSize") String tradeSize);

	@Query(nativeQuery = true, value = "SELECT u.trade_sze from NEC_310_16_B_16 u where u.seventy_five_insulation >= :p75 and u.trade_sze != :tradeSize and u.trade_sze NOT IN :notIn ORDER BY number_of_conductors, seventy_five_insulation LIMIT 1")
	String findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(@Param("p75") Integer insulation,
			@Param("tradeSize") String tradeSize, @Param("notIn") List<String> incorrectTradeSize);

	@Query(nativeQuery = true, value = "SELECT u.trade_sze from NEC_310_16_B_16 u where u.seventy_five_insulation >= :p75 and u.trade_sze != :tradeSize ORDER BY number_of_conductors, seventy_five_insulation LIMIT 1")
	String findTradeSizeBySeventyFiveInsulationAndTradeSize(@Param("p75") Integer insulation,
			@Param("tradeSize") String tradeSize);

}
