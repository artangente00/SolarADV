package com.PlayGroundAdv.Solar.service.restore_project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;

@Service
public class GetConduitConductorTab {

	final SharedUtils sharedUtils;

	public GetConduitConductorTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermitConduitConductorSectionEntitieResult getConduitConductorSection(HSSFSheet sheet) {
		try {
			PermitConduitConductorSectionEntitieResult entity = new PermitConduitConductorSectionEntitieResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				
				entity.setImgSegmentOne(sharedUtils.findStringValue(sheet, "img_segment_one"));
				entity.setImgSegmentTwo(sharedUtils.findStringValue(sheet, "img_segment_two"));
				entity.setImgSegmentThree(sharedUtils.findStringValue(sheet, "img_segment_three"));
				entity.setImgSegmentFour(sharedUtils.findStringValue(sheet, "img_segment_four"));
				entity.setImgSegmentFive(sharedUtils.findStringValue(sheet, "img_segment_five"));
				entity.setImgSegmentSix(sharedUtils.findStringValue(sheet, "img_segment_six"));
				entity.setImgSegmentSeven(sharedUtils.findStringValue(sheet, "img_segment_seven"));
				entity.setImgSegmentEight(sharedUtils.findStringValue(sheet, "img_segment_eight"));
				entity.setImgSegmentNine(sharedUtils.findStringValue(sheet, "img_segment_nine"));

				//////// Segment one ////////////
				entity.setConductorSize(sharedUtils.findStringValue(sheet, "conductor_size"));
				entity.setConductorType(sharedUtils.findStringValue(sheet, "conductor_type"));
				entity.setConduitSize(sharedUtils.findStringValue(sheet, "conduit_size"));
				entity.setConduitType(sharedUtils.findStringValue(sheet, "conduit_type"));
				// Others //
				entity.setConductorSizeOther(sharedUtils.findStringValue(sheet, "conductor_size_other"));
				entity.setConductorTypeOther(sharedUtils.findStringValue(sheet, "conductor_type_other"));
				entity.setConduitSizeOther(sharedUtils.findStringValue(sheet, "conduit_size_other"));
				entity.setConduitTypeOther(sharedUtils.findStringValue(sheet, "conduit_type_other"));

				//////// Segment Two ////////////
				entity.setConductorSizeTwo(sharedUtils.findStringValue(sheet, "conductor_size_two"));
				entity.setConductorTypeTwo(sharedUtils.findStringValue(sheet, "conductor_type_two"));
				entity.setConduitSizeTwo(sharedUtils.findStringValue(sheet, "conduit_size_two"));
				entity.setConduitTypeTwo(sharedUtils.findStringValue(sheet, "conduit_type_two"));
				// Others //
				entity.setConductorSizeTwoOther(sharedUtils.findStringValue(sheet, "conductor_size_two_other"));
				entity.setConductorTypeTwoOther(sharedUtils.findStringValue(sheet, "conductor_type_two_other"));
				entity.setConduitSizeTwoOther(sharedUtils.findStringValue(sheet, "conduit_size_two_other"));
				entity.setConduitTypeTwoOther(sharedUtils.findStringValue(sheet, "conduit_type_two_other"));

				//////// Segment Three ////////////
				entity.setConductorSizeThree(sharedUtils.findStringValue(sheet, "conductor_size_three"));
				entity.setConductorTypeThree(sharedUtils.findStringValue(sheet, "conductor_type_three"));
				entity.setConduitSizeThree(sharedUtils.findStringValue(sheet, "conduit_size_three"));
				entity.setConduitTypeThree(sharedUtils.findStringValue(sheet, "conduit_type_three"));
				// Others //
				entity.setConductorSizeThreeOther(sharedUtils.findStringValue(sheet, "conductor_size_three_other"));
				entity.setConductorTypeThreeOther(sharedUtils.findStringValue(sheet, "conductor_type_three_other"));
				entity.setConduitSizeThreeOther(sharedUtils.findStringValue(sheet, "conduit_size_three_other"));
				entity.setConduitTypeThreeOther(sharedUtils.findStringValue(sheet, "conduit_type_three_other"));

				//////// Segment Four ////////////
				entity.setConductorSizeFour(sharedUtils.findStringValue(sheet, "conductor_size_four"));
				entity.setConductorTypeFour(sharedUtils.findStringValue(sheet, "conductor_type_four"));
				entity.setConduitSizeFour(sharedUtils.findStringValue(sheet, "conduit_size_four"));
				entity.setConduitTypeFour(sharedUtils.findStringValue(sheet, "conduit_type_four"));
				// Others //
				entity.setConductorSizeFourOther(sharedUtils.findStringValue(sheet, "conductor_size_four_other"));
				entity.setConductorTypeFourOther(sharedUtils.findStringValue(sheet, "conductor_type_four_other"));
				entity.setConduitSizeFourOther(sharedUtils.findStringValue(sheet, "conduit_size_four_other"));
				entity.setConduitTypeFourOther(sharedUtils.findStringValue(sheet, "conduit_type_four_other"));

				//////// Segment Five ////////////
				entity.setConductorSizeFive(sharedUtils.findStringValue(sheet, "conductor_size_five"));
				entity.setConductorTypeFive(sharedUtils.findStringValue(sheet, "conductor_type_five"));
				entity.setConduitSizeFive(sharedUtils.findStringValue(sheet, "conduit_size_five"));
				entity.setConduitTypeFive(sharedUtils.findStringValue(sheet, "conduit_type_five"));
				// Others //
				entity.setConductorSizeFiveOther(sharedUtils.findStringValue(sheet, "conductor_size_five_other"));
				entity.setConductorTypeFiveOther(sharedUtils.findStringValue(sheet, "conductor_type_five_other"));
				entity.setConduitSizeFiveOther(sharedUtils.findStringValue(sheet, "conduit_size_five_other"));
				entity.setConduitTypeFiveOther(sharedUtils.findStringValue(sheet, "conduit_type_five_other"));

				//////// Segment Six ////////////
				entity.setConductorSizeSix(sharedUtils.findStringValue(sheet, "conductor_size_six"));
				entity.setConductorTypeSix(sharedUtils.findStringValue(sheet, "conductor_type_six"));
				entity.setConduitSizeSix(sharedUtils.findStringValue(sheet, "conduit_size_six"));
				entity.setConduitTypeSix(sharedUtils.findStringValue(sheet, "conduit_type_six"));
				// Others //
				entity.setConductorSizeSixOther(sharedUtils.findStringValue(sheet, "conductor_size_six_other"));
				entity.setConductorTypeSixOther(sharedUtils.findStringValue(sheet, "conductor_type_six_other"));
				entity.setConduitSizeSixOther(sharedUtils.findStringValue(sheet, "conduit_size_six_other"));
				entity.setConduitTypeSixOther(sharedUtils.findStringValue(sheet, "conduit_type_six_other"));

				//////// Segment Seven ////////////
				entity.setConductorSizeSeven(sharedUtils.findStringValue(sheet, "conductor_size_seven"));
				entity.setConductorTypeSeven(sharedUtils.findStringValue(sheet, "conductor_type_seven"));
				entity.setConduitSizeSeven(sharedUtils.findStringValue(sheet, "conduit_size_seven"));
				entity.setConduitTypeSeven(sharedUtils.findStringValue(sheet, "conduit_type_seven"));
				// Others //
				entity.setConductorSizeSevenOther(sharedUtils.findStringValue(sheet, "conductor_size_seven_other"));
				entity.setConductorTypeSevenOther(sharedUtils.findStringValue(sheet, "conductor_type_seven_other"));
				entity.setConduitSizeSevenOther(sharedUtils.findStringValue(sheet, "conduit_size_seven_other"));
				entity.setConduitTypeSevenOther(sharedUtils.findStringValue(sheet, "conduit_type_seven_other"));

				//////// Segment Eight ////////////
				entity.setConductorSizeEight(sharedUtils.findStringValue(sheet, "conductor_size_eight"));
				entity.setConductorTypeEight(sharedUtils.findStringValue(sheet, "conductor_type_eight"));
				entity.setConduitSizeEight(sharedUtils.findStringValue(sheet, "conduit_size_eight"));
				entity.setConduitTypeEight(sharedUtils.findStringValue(sheet, "conduit_type_eight"));
				// Others //
				entity.setConductorSizeEightOther(sharedUtils.findStringValue(sheet, "conductor_size_eight_other"));
				entity.setConductorTypeEightOther(sharedUtils.findStringValue(sheet, "conductor_type_eight_other"));
				entity.setConduitSizeEightOther(sharedUtils.findStringValue(sheet, "conduit_size_eight_other"));
				entity.setConduitTypeEightOther(sharedUtils.findStringValue(sheet, "conduit_type_eight_other"));

				//////////// QTY //////////////////////
				entity.setQtySegmentOne(sharedUtils.findIntValue(sheet, "qty_segment_one"));
				entity.setQtySegmentTwo(sharedUtils.findIntValue(sheet, "qty_segment_two"));
				entity.setQtySegmentThree(sharedUtils.findIntValue(sheet, "qty_segment_three"));
				entity.setQtySegmentFour(sharedUtils.findIntValue(sheet, "qty_segment_four"));
				entity.setQtySegmentFive(sharedUtils.findIntValue(sheet, "qty_segment_five"));
				entity.setQtySegmentSix(sharedUtils.findIntValue(sheet, "qty_segment_six"));
				entity.setQtySegmentSeven(sharedUtils.findIntValue(sheet, "qty_segment_seven"));
				entity.setQtySegmentEight(sharedUtils.findIntValue(sheet, "qty_segment_eight"));
				entity.setQtySegmentNine(sharedUtils.findIntValue(sheet, "qty_segment_nne"));
				entity.setQtySegmentTen(sharedUtils.findIntValue(sheet, "qty_segment_ten"));
				entity.setQtySegmentEleven(sharedUtils.findIntValue(sheet, "qty_segment_eleven"));

				//////////// QTY //////////////////////
				entity.setConductorQty(sharedUtils.findStringValue(sheet, "conductor_qty"));
				entity.setConductorQtyTwo(sharedUtils.findStringValue(sheet, "conductor_qty_two"));
				entity.setConductorQtyThree(sharedUtils.findStringValue(sheet, "conductor_qty_three"));
				entity.setConductorQtyFour(sharedUtils.findStringValue(sheet, "conductor_qty_four"));
				entity.setConductorQtyFive(sharedUtils.findStringValue(sheet, "conductor_qty_five"));
				entity.setConductorQtySix(sharedUtils.findStringValue(sheet, "conductor_qty_six"));
				entity.setConductorQtySeven(sharedUtils.findStringValue(sheet, "conductor_qty_seven"));
				entity.setConductorQtyEight(sharedUtils.findStringValue(sheet, "conductor_qty_eight"));

				entity.setConductorTypeTen(sharedUtils.findStringValue(sheet, "conductor_type_ten"));
				entity.setConductorSizeTen(sharedUtils.findStringValue(sheet, "conductor_size_ten"));
				entity.setConduitTypeTen(sharedUtils.findStringValue(sheet, "conduit_type_ten"));
				entity.setConduitSizeTen(sharedUtils.findStringValue(sheet, "conduit_size_ten"));
				entity.setConductorQtyTen(sharedUtils.findStringValue(sheet, "conductor_qty_ten"));
				entity.setConductorTypeNine(sharedUtils.findStringValue(sheet, "conductor_type_nine"));
				entity.setConductorSizeNine(sharedUtils.findStringValue(sheet, "conductor_size_nine"));
				entity.setConduitTypeNine(sharedUtils.findStringValue(sheet, "conduit_type_nine"));
				entity.setConduitSizeNine(sharedUtils.findStringValue(sheet, "conduit_size_nine"));
				entity.setConductorQtyNine(sharedUtils.findStringValue(sheet, "conductor_qty_nine"));
				entity.setConductorTypeTwelve(sharedUtils.findStringValue(sheet, "conductor_type_twelve"));
				entity.setConductorSizeTwelve(sharedUtils.findStringValue(sheet, "conductor_size_twelve"));
				entity.setConduitTypeTwelve(sharedUtils.findStringValue(sheet, "conduit_type_twelve"));
				entity.setConduitSizeTwelve(sharedUtils.findStringValue(sheet, "conduit_size_twelve"));
				entity.setConductorQtyTwelve(sharedUtils.findStringValue(sheet, "conductor_qty_twelve"));
				entity.setConductorTypeEleven(sharedUtils.findStringValue(sheet, "conductor_type_eleven"));
				entity.setConductorSizeEleven(sharedUtils.findStringValue(sheet, "conductor_size_eleven"));
				entity.setConduitTypeEleven(sharedUtils.findStringValue(sheet, "conduit_type_eleven"));
				entity.setConduitSizeEleven(sharedUtils.findStringValue(sheet, "conduit_size_eleven"));
				entity.setConductorQtyEleven(sharedUtils.findStringValue(sheet, "conductor_qty_eleven"));
				entity.setConductorTypeNineOther(sharedUtils.findStringValue(sheet, "conductor_type_nine_other"));
				entity.setConductorSizeNineOther(sharedUtils.findStringValue(sheet, "conductor_size_nine_other"));
				entity.setConduitTypeNineOther(sharedUtils.findStringValue(sheet, "conduit_type_nine_other"));
				entity.setConduitSizeNineOther(sharedUtils.findStringValue(sheet, "conduit_size_nine_other"));
				entity.setConductorTypeTenOther(sharedUtils.findStringValue(sheet, "conductor_type_ten_other"));
				entity.setConductorSizeTenOther(sharedUtils.findStringValue(sheet, "conductor_size_ten_other"));
				entity.setConduitTypeTenOther(sharedUtils.findStringValue(sheet, "conduit_type_ten_other"));
				entity.setConduitSizeTenOther(sharedUtils.findStringValue(sheet, "conduit_size_ten_other"));
				entity.setConductorTypeElevenOther(sharedUtils.findStringValue(sheet, "conductor_type_eleven_other"));
				entity.setConductorSizeElevenOther(sharedUtils.findStringValue(sheet, "conductor_size_eleven_other"));
				entity.setConduitTypeElevenOther(sharedUtils.findStringValue(sheet, "conduit_type_eleven_other"));
				entity.setConduitSizeElevenOther(sharedUtils.findStringValue(sheet, "conduit_size_eleven_other"));
				entity.setConductorTypeTwelveOther(sharedUtils.findStringValue(sheet, "conductor_type_twelve_other"));
				entity.setConductorSizeTwelveOther(sharedUtils.findStringValue(sheet, "conductor_size_twelve_other"));
				entity.setConduitTypeTwelveOther(sharedUtils.findStringValue(sheet, "conduit_type_twelve_other"));
				entity.setConduitSizeTwelveOther(sharedUtils.findStringValue(sheet, "conduit_size_twelve_other"));
				entity.setConductorQtyOther(sharedUtils.findIntValue(sheet, "conductor_qty_other"));
				entity.setConductorQtyTwoOther(sharedUtils.findIntValue(sheet, "conductor_qty_two_other"));
				entity.setConductorQtyThreeOther(sharedUtils.findIntValue(sheet, "conductor_qty_three_other"));
				entity.setConductorQtyFourOther(sharedUtils.findIntValue(sheet, "conductor_qty_four_other"));
				entity.setConductorQtyFiveOther(sharedUtils.findIntValue(sheet, "conductor_qty_five_other"));
				entity.setConductorQtySixOther(sharedUtils.findIntValue(sheet, "conductor_qty_six_other"));
				entity.setConductorQtySevenOther(sharedUtils.findIntValue(sheet, "conductor_qty_seven_other"));
				entity.setConductorQtyEightOther(sharedUtils.findIntValue(sheet, "conductor_qty_eight_other"));
				entity.setConductorQtyNineOther(sharedUtils.findIntValue(sheet, "conductor_qty_nine_other"));
				entity.setConductorQtyTenOther(sharedUtils.findIntValue(sheet, "conductor_qty_ten_other"));
				entity.setConductorQtyElevenOther(sharedUtils.findIntValue(sheet, "conductor_qty_eleven_other"));
				entity.setComponentOrder(sharedUtils.findStringValue(sheet, "component_order"));
				entity.setMapFromUserInput(sharedUtils.findBooleanValue(sheet, "map_from_user_input"));

				entity.setQtySegmentNineTwo(sharedUtils.findIntValue(sheet, "qty_segment_nne_two"));
				entity.setConductorQtyNineTwo(sharedUtils.findStringValue(sheet, "conductor_qty_nine_two"));
				entity.setConductorTypeNineTwo(sharedUtils.findStringValue(sheet, "conductor_type_nine_two"));
				entity.setConductorSizeNineTwo(sharedUtils.findStringValue(sheet, "conductor_size_nine_two"));
				entity.setConduitTypeNineTwo(sharedUtils.findStringValue(sheet, "conduit_type_nine_two"));
				entity.setConduitSizeNineTwo(sharedUtils.findStringValue(sheet, "conduit_size_nine_two"));
				entity.setConductorQtyNineTwoOther(sharedUtils.findIntValue(sheet, "conductor_qty_nine_two_other"));
				entity.setConductorTypeNineTwoOther(sharedUtils.findStringValue(sheet, "conductor_type_nine_two_other"));
				entity.setConductorSizeNineTwoOther(sharedUtils.findStringValue(sheet, "conductor_size_nine_two_other"));
				entity.setConduitTypeNineTwoOther(sharedUtils.findStringValue(sheet, "conduit_type_nine_two_other"));
				entity.setConduitSizeNineTwoOther(sharedUtils.findStringValue(sheet, "conduit_size_nine_two_other"));
				entity.setImgSegmentNineTwo(sharedUtils.findStringValue(sheet, "img_segment_nine_two"));
				entity.setConductorTypeExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_existing"));
				entity.setConductorSizeExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_existing"));
				entity.setConduitTypeExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_existing"));
				entity.setConduitSizeExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_existing"));
				entity.setConductorTypeTwoExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_two_existing"));
				entity.setConductorSizeTwoExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_two_existing"));
				entity.setConduitTypeTwoExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_two_existing"));
				entity.setConduitSizeTwoExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_two_existing"));
				entity.setConductorTypeThreeExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_three_existing"));
				entity.setConductorSizeThreeExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_three_existing"));
				entity.setConduitTypeThreeExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_three_existing"));
				entity.setConduitSizeThreeExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_three_existing"));
				entity.setConductorTypeFourExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_four_existing"));
				entity.setConductorSizeFourExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_four_existing"));
				entity.setConduitTypeFourExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_four_existing"));
				entity.setConduitSizeFourExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_four_existing"));
				entity.setConductorTypeFiveExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_five_existing"));
				entity.setConductorSizeFiveExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_five_existing"));
				entity.setConduitTypeFiveExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_five_existing"));
				entity.setConduitSizeFiveExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_five_existing"));
				entity.setConductorTypeSixExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_six_existing"));
				entity.setConductorSizeSixExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_six_existing"));
				entity.setConduitTypeSixExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_six_existing"));
				entity.setConduitSizeSixExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_six_existing"));
				entity.setConductorTypeSevenExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_seven_existing"));
				entity.setConductorSizeSevenExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_seven_existing"));
				entity.setConduitTypeSevenExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_seven_existing"));
				entity.setConduitSizeSevenExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_seven_existing"));
				entity.setConductorTypeEightExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_eight_existing"));
				entity.setConductorSizeEightExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_eight_existing"));
				entity.setConduitTypeEightExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_eight_existing"));
				entity.setConduitSizeEightExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_eight_existing"));
				entity.setConductorTypeNineExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_nine_existing"));
				entity.setConductorSizeNineExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_nine_existing"));
				entity.setConduitTypeNineExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_nine_existing"));
				entity.setConduitSizeNineExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_nine_existing"));
				entity.setConductorTypeNineTwoExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_nine_two_existing"));
				entity.setConductorSizeNineTwoExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_nine_two_existing"));
				entity.setConduitTypeNineTwoExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_nine_two_existing"));
				entity.setConduitSizeNineTwoExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_nine_two_existing"));
				entity.setConductorTypeTenExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_ten_existing"));
				entity.setConductorSizeTenExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_ten_existing"));
				entity.setConduitTypeTenExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_ten_existing"));
				entity.setConduitSizeTenExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_ten_existing"));
				entity.setConductorTypeElevenExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_eleven_existing"));
				entity.setConductorSizeElevenExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_eleven_existing"));
				entity.setConduitTypeElevenExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_eleven_existing"));
				entity.setConduitSizeElevenExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_eleven_existing"));
				
				entity.setConductorNeutral(sharedUtils.findBooleanValue(sheet, "conductor_neutral"));
				entity.setConductorNeutralTwo(sharedUtils.findBooleanValue(sheet, "conductor_neutral_two"));
				entity.setConductorNeutralThree(sharedUtils.findBooleanValue(sheet, "conductor_neutral_three"));
				entity.setConductorNeutralFour(sharedUtils.findBooleanValue(sheet, "conductor_neutral_four"));
				entity.setConductorNeutralFive(sharedUtils.findBooleanValue(sheet, "conductor_neutral_five"));
				entity.setConductorNeutralSix(sharedUtils.findBooleanValue(sheet, "conductor_neutral_six"));
				entity.setConductorNeutralSeven(sharedUtils.findBooleanValue(sheet, "conductor_neutral_seven"));
				entity.setConductorNeutralEight(sharedUtils.findBooleanValue(sheet, "conductor_neutral_eight"));
				entity.setConductorNeutralNine(sharedUtils.findBooleanValue(sheet, "conductor_neutral_nine"));
				entity.setConductorNeutralNineTwo(sharedUtils.findBooleanValue(sheet, "conductor_neutral_nine_two"));
				entity.setConductorNeutralTen(sharedUtils.findBooleanValue(sheet, "conductor_neutral_ten"));
				entity.setConductorNeutralEleven(sharedUtils.findBooleanValue(sheet, "conductor_neutral_eleven"));
				
				entity.setQtySegmentTwelve(sharedUtils.findIntValue(sheet, "qty_segment_twelve"));
				entity.setConductorTypeTwelveExisting(sharedUtils.findBooleanValue(sheet, "conductor_type_twelve_existing"));
				entity.setConductorSizeTwelveExisting(sharedUtils.findBooleanValue(sheet, "conductor_size_twelve_existing"));
				entity.setConduitTypeTwelveExisting(sharedUtils.findBooleanValue(sheet, "conduit_type_twelve_existing"));
				entity.setConduitSizeTwelveExisting(sharedUtils.findBooleanValue(sheet, "conduit_size_twelve_existing"));
				entity.setConductorQtyTwelveOther(sharedUtils.findIntValue(sheet, "conductor_qty_twelve_other"));
				entity.setConductorNeutralTwelve(sharedUtils.findBooleanValue(sheet, "conductor_neutral_twelve"));
				
				entity.setCircuitEnvironment(sharedUtils.findStringValue(sheet, "circuit_environment"));
				entity.setCircuitEnvironmentTwo(sharedUtils.findStringValue(sheet, "circuit_environment_two"));
				entity.setCircuitEnvironmentThree(sharedUtils.findStringValue(sheet, "circuit_environment_three"));
				entity.setCircuitEnvironmentFour(sharedUtils.findStringValue(sheet, "circuit_environment_four"));
				entity.setCircuitEnvironmentFive(sharedUtils.findStringValue(sheet, "circuit_environment_five"));
				entity.setCircuitEnvironmentSix(sharedUtils.findStringValue(sheet, "circuit_environment_six"));
				entity.setCircuitEnvironmentSeven(sharedUtils.findStringValue(sheet, "circuit_environment_seven"));
				entity.setCircuitEnvironmentEight(sharedUtils.findStringValue(sheet, "circuit_environment_eight"));
				entity.setCircuitEnvironmentNine(sharedUtils.findStringValue(sheet, "circuit_environment_nine"));
				entity.setCircuitEnvironmentNineTwo(sharedUtils.findStringValue(sheet, "circuit_environment_nine_two"));
				entity.setCircuitEnvironmentTen(sharedUtils.findStringValue(sheet, "circuit_environment_ten"));
				entity.setCircuitEnvironmentEleven(sharedUtils.findStringValue(sheet, "circuit_environment_eleven"));
				entity.setCircuitEnvironmentTwelve(sharedUtils.findStringValue(sheet, "circuit_environment_twelve"));
				
				entity.setCircuitEnvironmentChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_changed"));
				entity.setCircuitEnvironmentTwoChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_two_changed"));
				entity.setCircuitEnvironmentThreeChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_three_changed"));
				entity.setCircuitEnvironmentFourChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_four_changed"));
				entity.setCircuitEnvironmentFiveChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_five_changed"));
				entity.setCircuitEnvironmentSixChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_six_changed"));
				entity.setCircuitEnvironmentSevenChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_seven_changed"));
				entity.setCircuitEnvironmentEightChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_eight_changed"));
				entity.setCircuitEnvironmentNineChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_nine_changed"));
				entity.setCircuitEnvironmentNineTwoChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_nine_two_changed"));
				entity.setCircuitEnvironmentTenChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_ten_changed"));
				entity.setCircuitEnvironmentElevenChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_eleven_changed"));
				entity.setCircuitEnvironmentTwelveChanged(sharedUtils.findBooleanValue(sheet, "circuit_environment_twelve_changed"));
				
				entity.setCircuitLengthOne(sharedUtils.findFloatValue(sheet, "circuit_length_one"));
				entity.setCircuitLengthTwo(sharedUtils.findFloatValue(sheet, "circuit_length_two"));
				entity.setCircuitLengthThree(sharedUtils.findFloatValue(sheet, "circuit_length_three"));
				entity.setCircuitLengthFour(sharedUtils.findFloatValue(sheet, "circuit_length_four"));
				entity.setCircuitLengthFive(sharedUtils.findFloatValue(sheet, "circuit_length_five"));
				entity.setCircuitLengthSix(sharedUtils.findFloatValue(sheet, "circuit_length_six"));
				entity.setCircuitLengthSeven(sharedUtils.findFloatValue(sheet, "circuit_length_seven"));
				entity.setCircuitLengthEight(sharedUtils.findFloatValue(sheet, "circuit_length_eight"));
				entity.setCircuitLengthNine(sharedUtils.findFloatValue(sheet, "circuit_length_nine"));
				entity.setCircuitLengthNineTwo(sharedUtils.findFloatValue(sheet, "circuit_length_nine_two"));
				entity.setCircuitLengthTen(sharedUtils.findFloatValue(sheet, "circuit_length_ten"));
				entity.setCircuitLengthEleven(sharedUtils.findFloatValue(sheet, "circuit_length_eleven"));
				entity.setCircuitLengthTwelve(sharedUtils.findFloatValue(sheet, "circuit_length_twelve"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitConduitConductorSectionEntitieResult();
		}
	}

}
