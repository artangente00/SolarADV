package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * @author Arij
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "ConduitConductorCircuitEntity")
public class ConduitConductorCircuitEntity {
private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="hibernate_sequence37", sequenceName = "hibernate_sequence37", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence37")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="CIRCUIT_LENGTH")
	private Integer circuitLength;
	
	@Column(name="CONDUCTOR_QTY")
	private String conductorQty;
	
	@Column(name="CONDUCTOR_SIZE")
	private String conductorSize;
	
	@Column(name="CONDUCTOR_TYPE")
	private String conductorType;
	
	@Column(name="CONDUIT_SIZE")
	private String conduitSize;
	
	@Column(name="CONDUIT_TYPE")
	private String conduitType;
	
	@Column(name="CONDUCTOR_SIZE_OTHER")
	private String conductorSizeOther;
	
	@Column(name="CONDUCTOR_TYPE_OTHER")
	private String conductorTypeOther;
	
	@Column(name="CONDUIT_SIZE_OTHER")
	private String conduitSizeOther;
	
	@Column(name="CONDUIT_TYPE_OTHER")
	private String conduitTypeOther;
	
	@Column(name="CONDUCTOR_QTY_TWO")
	private String conductorQtyTwo;
	
	@Column(name="CONDUCTOR_SIZE_TWO")
	private String conductorSizeTwo;
	
	@Column(name="CONDUCTOR_TYPE_TWO")
	private String conductorTypeTwo;
	
	@Column(name="CONDUIT_SIZE_TWO")
	private String conduitSizeTwo;
	
	@Column(name="CONDUIT_TYPE_TWO")
	private String conduitTypeTwo;
	
	@Column(name="CONDUCTOR_SIZE_TWO_OTHER")
	private String conductorSizeTwoOther;
	
	@Column(name="CONDUCTOR_TYPE_TWO_OTHER")
	private String conductorTypeTwoOther;
	
	@Column(name="CONDUIT_SIZE_TWO_OTHER")
	private String conduitSizeTwoOther;
	
	@Column(name="CONDUIT_TYPE_TWO_OTHER")
	private String conduitTypeTwoOther;

	@Column(name="CONDUCTOR_QTY_THREE")
	private String conductorQtyThree;
	
	@Column(name="CONDUCTOR_SIZE_THREE")
	private String conductorSizeThree;
	
	@Column(name="CONDUCTOR_TYPE_THREE")
	private String conductorTypeThree;
	
	@Column(name="CONDUIT_SIZE_THREE")
	private String conduitSizeThree;
	
	@Column(name="CONDUIT_TYPE_THREE")
	private String conduitTypeThree;
	
	@Column(name="CONDUCTOR_SIZE_THREE_OTHER")
	private String conductorSizeThreeOther;
	
	@Column(name="CONDUCTOR_TYPE_THREE_OTHER")
	private String conductorTypeThreeOther;
	
	@Column(name="CONDUIT_SIZE_THREE_OTHER")
	private String conduitSizeThreeOther;
	
	@Column(name="CONDUIT_TYPE_THREE_OTHER")
	private String conduitTypeThreeOther;

	@Column(name="CONDUCTOR_QTY_FOUR")
	private String conductorQtyFour;
	
	@Column(name="CONDUCTOR_SIZE_FOUR")
	private String conductorSizeFour;
	
	@Column(name="CONDUCTOR_TYPE_FOUR")
	private String conductorTypeFour;
	
	@Column(name="CONDUIT_SIZE_FOUR")
	private String conduitSizeFour;
	
	@Column(name="CONDUIT_TYPE_FOUR")
	private String conduitTypeFour;
	
	@Column(name="CONDUCTOR_SIZE_FOUR_OTHER")
	private String conductorSizeFourOther;
	
	@Column(name="CONDUCTOR_TYPE_FOUR_OTHER")
	private String conductorTypeFourOther;
	
	@Column(name="CONDUIT_SIZE_FOUR_OTHER")
	private String conduitSizeFourOther;
	
	@Column(name="CONDUIT_TYPE_FOUR_OTHER")
	private String conduitTypeFourOther;

	@Column(name="CONDUCTOR_QTY_FIVE")
	private String conductorQtyFive;
	
	@Column(name="CONDUCTOR_SIZE_FIVE")
	private String conductorSizeFive;
	
	@Column(name="CONDUCTOR_TYPE_FIVE")
	private String conductorTypeFive;
	
	@Column(name="CONDUIT_SIZE_FIVE")
	private String conduitSizeFive;
	
	@Column(name="CONDUIT_TYPE_FIVE")
	private String conduitTypeFive;
	
	@Column(name="CONDUCTOR_SIZE_FIVE_OTHER")
	private String conductorSizeFiveOther;
	
	@Column(name="CONDUCTOR_TYPE_FIVE_OTHER")
	private String conductorTypeFiveOther;
	
	@Column(name="CONDUIT_SIZE_FIVE_OTHER")
	private String conduitSizeFiveOther;
	
	@Column(name="CONDUIT_TYPE_FIVE_OTHER")
	private String conduitTypeFiveOther;

	@Column(name="CONDUCTOR_QTY_SIX")
	private String conductorQtySix;
	
	@Column(name="CONDUCTOR_SIZE_SIX")
	private String conductorSizeSix;
	
	@Column(name="CONDUCTOR_TYPE_SIX")
	private String conductorTypeSix;
	
	@Column(name="CONDUIT_SIZE_SIX")
	private String conduitSizeSix;
	
	@Column(name="CONDUIT_TYPE_SIX")
	private String conduitTypeSix;
	
	@Column(name="CONDUCTOR_SIZE_SIX_OTHER")
	private String conductorSizeSixOther;
	
	@Column(name="CONDUCTOR_TYPE_SIX_OTHER")
	private String conductorTypeSixOther;
	
	@Column(name="CONDUIT_SIZE_SIX_OTHER")
	private String conduitSizeSixOther;
	
	@Column(name="CONDUIT_TYPE_SIX_OTHER")
	private String conduitTypeSixOther;

	@Column(name="CONDUCTOR_QTY_SEVEN")
	private String conductorQtySeven;
	
	@Column(name="CONDUCTOR_SIZE_SEVEN")
	private String conductorSizeSeven;
	
	@Column(name="CONDUCTOR_TYPE_SEVEN")
	private String conductorTypeSeven;
	
	@Column(name="CONDUIT_SIZE_SEVEN")
	private String conduitSizeSeven;
	
	@Column(name="CONDUIT_TYPE_SEVEN")
	private String conduitTypeSeven;
	
	@Column(name="CONDUCTOR_SIZE_SEVEN_OTHER")
	private String conductorSizeSevenOther;
	
	@Column(name="CONDUCTOR_TYPE_SEVEN_OTHER")
	private String conductorTypeSevenOther;
	
	@Column(name="CONDUIT_SIZE_SEVEN_OTHER")
	private String conduitSizeSevenOther;
	
	@Column(name="CONDUIT_TYPE_SEVEN_OTHER")
	private String conduitTypeSevenOther;

	@Column(name="CONDUCTOR_QTY_EIGHT")
	private String conductorQtyEight;
	
	@Column(name="CONDUCTOR_SIZE_EIGHT")
	private String conductorSizeEight;
	
	@Column(name="CONDUCTOR_TYPE_EIGHT")
	private String conductorTypeEight;
	
	@Column(name="CONDUIT_SIZE_EIGHT")
	private String conduitSizeEight;
	
	@Column(name="CONDUIT_TYPE_EIGHT")
	private String conduitTypeEight;
	
	@Column(name="CONDUCTOR_SIZE_EIGHT_OTHER")
	private String conductorSizeEightOther;
	
	@Column(name="CONDUCTOR_TYPE_EIGHT_OTHER")
	private String conductorTypeEightOther;
	
	@Column(name="CONDUIT_SIZE_EIGHT_OTHER")
	private String conduitSizeEightOther;
	
	@Column(name="CONDUIT_TYPE_EIGHT_OTHER")
	private String conduitTypeEightOther;
	
	@Column(name="CONDUCTOR_TYPE_NINE")
	private String conductorTypeNine;
	
	@Column(name="CONDUCTOR_SIZE_NINE")
	private String conductorSizeNine;
	
	@Column(name="CONDUIT_TYPE_NINE")
	private String conduitTypeNine;
	
	@Column(name="CONDUIT_SIZE_NINE")
	private String conduitSizeNine;
	
	@Column(name="CONDUCTOR_QTY_NINE")
	private String conductorQtyNine;
	
	@Column(name="CONDUCTOR_TYPE_NINE_OTHER")
	private String conductorTypeNineOther;
	
	@Column(name="CONDUCTOR_SIZE_NINE_OTHER")
	private String conductorSizeNineOther;
	
	@Column(name="CONDUIT_TYPE_NINE_OTHER")
	private String conduitTypeNineOther;
	
	@Column(name="CONDUIT_SIZE_NINE_OTHER")
	private String conduitSizeNineOther;
	
	@Column(name="CONDUCTOR_TYPE_TEN")
	private String conductorTypeTen;
	
	@Column(name="CONDUCTOR_SIZE_TEN")
	private String conductorSizeTen;
	
	@Column(name="CONDUIT_TYPE_TEN")
	private String conduitTypeTen;
	
	@Column(name="CONDUIT_SIZE_TEN")
	private String conduitSizeTen;
	
	@Column(name="CONDUCTOR_QTY_TEN")
	private String conductorQtyTen;
	
	@Column(name="CONDUCTOR_TYPE_TEN_OTHER")
	private String conductorTypeTenOther;
	
	@Column(name="CONDUCTOR_SIZE_TEN_OTHER")
	private String conductorSizeTenOther;
	
	@Column(name="CONDUIT_TYPE_TEN_OTHER")
	private String conduitTypeTenOther;
	
	@Column(name="CONDUIT_SIZE_TEN_OTHER")
	private String conduitSizeTenOther;
	
	@Column(name="CONDUCTOR_TYPE_ELEVEN")
	private String conductorTypeEleven;
	
	@Column(name="CONDUCTOR_SIZE_ELEVEN")
	private String conductorSizeEleven;
	
	@Column(name="CONDUIT_TYPE_ELEVEN")
	private String conduitTypeEleven;
	
	@Column(name="CONDUIT_SIZE_ELEVEN")
	private String conduitSizeEleven;
	
	@Column(name="CONDUCTOR_QTY_ELEVEN")
	private String conductorQtyEleven;
	
	@Column(name="CONDUCTOR_TYPE_ELEVEN_OTHER")
	private String conductorTypeElevenOther;
	
	@Column(name="CONDUCTOR_SIZE_ELEVEN_OTHER")
	private String conductorSizeElevenOther;
	
	@Column(name="CONDUIT_TYPE_ELEVEN_OTHER")
	private String conduitTypeElevenOther;
	
	@Column(name="CONDUIT_SIZE_ELEVEN_OTHER")
	private String conduitSizeElevenOther;
	
	@Column(name="CONDUCTOR_QTY_OTHER")
	private Integer conductorQtyOther;
	
	@Column(name="CONDUCTOR_QTY_TWO_OTHER")
	private Integer conductorQtyTwoOther;
	
	@Column(name="CONDUCTOR_QTY_THREE_OTHER")
	private Integer conductorQtyThreeOther;
	
	@Column(name="CONDUCTOR_QTY_FOUR_OTHER")
	private Integer conductorQtyFourOther;
	
	@Column(name="CONDUCTOR_QTY_FIVE_OTHER")
	private Integer conductorQtyFiveOther;
	
	@Column(name="CONDUCTOR_QTY_SIX_OTHER")
	private Integer conductorQtySixOther;
	
	@Column(name="CONDUCTOR_QTY_SEVEN_OTHER")
	private Integer conductorQtySevenOther;
	
	@Column(name="CONDUCTOR_QTY_EIGHT_OTHER")
	private Integer conductorQtyEightOther;
	
	@Column(name="CONDUCTOR_QTY_NINE_OTHER")
	private Integer conductorQtyNineOther;
	
	@Column(name="CONDUCTOR_QTY_TEN_OTHER")
	private Integer conductorQtyTenOther;
	
	@Column(name="CONDUCTOR_QTY_ELEVEN_OTHER")
	private Integer conductorQtyElevenOther;
	
	@Column(name="CONDUCTOR_TYPE_NINE_TWO")
	private String conductorTypeNineTwo;
	
	@Column(name="CONDUCTOR_SIZE_NINE_TWO")
	private String conductorSizeNineTwo;
	
	@Column(name="CONDUIT_TYPE_NINE_TWO")
	private String conduitTypeNineTwo;
	
	@Column(name="CONDUIT_SIZE_NINE_TWO")
	private String conduitSizeNineTwo;
	
	@Column(name="CONDUCTOR_QTY_NINE_TWO")
	private String conductorQtyNineTwo;
	
	@Column(name="CONDUCTOR_TYPE_NINE_TWO_OTHER")
	private String conductorTypeNineTwoOther;
	
	@Column(name="CONDUCTOR_SIZE_NINE_TWO_OTHER")
	private String conductorSizeNineTwoOther;
	
	@Column(name="CONDUIT_TYPE_NINE_TWO_OTHER")
	private String conduitTypeNineTwoOther;
	
	@Column(name="CONDUIT_SIZE_NINE_TWO_OTHER")
	private String conduitSizeNineTwoOther;
	
	@Column(name="CONDUCTOR_QTY_NINE_TWO_OTHER")
	private Integer conductorQtyNineTwoOther;
	
	@Column(name="CONDUCTOR_TYPE_EXISTING")
	private Boolean conductorTypeExisting;
	
	@Column(name="CONDUCTOR_SIZE_EXISTING")
	private Boolean conductorSizeExisting;
	
	@Column(name="CONDUIT_TYPE_EXISTING")
	private Boolean conduitTypeExisting;
	
	@Column(name="CONDUIT_SIZE_EXISTING")
	private Boolean conduitSizeExisting;
	
	@Column(name="CONDUCTOR_TYPE_TWO_EXISTING")
	private Boolean conductorTypeTwoExisting;
	
	@Column(name="CONDUCTOR_SIZE_TWO_EXISTING")
	private Boolean conductorSizeTwoExisting;
	
	@Column(name="CONDUIT_TYPE_TWO_EXISTING")
	private Boolean conduitTypeTwoExisting;
	
	@Column(name="CONDUIT_SIZE_TWO_EXISTING")
	private Boolean conduitSizeTwoExisting;
	
	@Column(name="CONDUCTOR_TYPE_THREE_EXISTING")
	private Boolean conductorTypeThreeExisting;
	
	@Column(name="CONDUCTOR_SIZE_THREE_EXISTING")
	private Boolean conductorSizeThreeExisting;
	
	@Column(name="CONDUIT_TYPE_THREE_EXISTING")
	private Boolean conduitTypeThreeExisting;
	
	@Column(name="CONDUIT_SIZE_THREE_EXISTING")
	private Boolean conduitSizeThreeExisting;
	
	@Column(name="CONDUCTOR_TYPE_FOUR_EXISTING")
	private Boolean conductorTypeFourExisting;
	
	@Column(name="CONDUCTOR_SIZE_FOUR_EXISTING")
	private Boolean conductorSizeFourExisting;
	
	@Column(name="CONDUIT_TYPE_FOUR_EXISTING")
	private Boolean conduitTypeFourExisting;
	
	@Column(name="CONDUIT_SIZE_FOUR_EXISTING")
	private Boolean conduitSizeFourExisting;
	
	@Column(name="CONDUCTOR_TYPE_FIVE_EXISTING")
	private Boolean conductorTypeFiveExisting;
	
	@Column(name="CONDUCTOR_SIZE_FIVE_EXISTING")
	private Boolean conductorSizeFiveExisting;
	
	@Column(name="CONDUIT_TYPE_FIVE_EXISTING")
	private Boolean conduitTypeFiveExisting;
	
	@Column(name="CONDUIT_SIZE_FIVE_EXISTING")
	private Boolean conduitSizeFiveExisting;
	
	@Column(name="CONDUCTOR_TYPE_SIX_EXISTING")
	private Boolean conductorTypeSixExisting;
	
	@Column(name="CONDUCTOR_SIZE_SIX_EXISTING")
	private Boolean conductorSizeSixExisting;
	
	@Column(name="CONDUIT_TYPE_SIX_EXISTING")
	private Boolean conduitTypeSixExisting;
	
	@Column(name="CONDUIT_SIZE_SIX_EXISTING")
	private Boolean conduitSizeSixExisting;
	
	@Column(name="CONDUCTOR_TYPE_SEVEN_EXISTING")
	private Boolean conductorTypeSevenExisting;
	
	@Column(name="CONDUCTOR_SIZE_SEVEN_EXISTING")
	private Boolean conductorSizeSevenExisting;
	
	@Column(name="CONDUIT_TYPE_SEVEN_EXISTING")
	private Boolean conduitTypeSevenExisting;
	
	@Column(name="CONDUIT_SIZE_SEVEN_EXISTING")
	private Boolean conduitSizeSevenExisting;
	
	@Column(name="CONDUCTOR_TYPE_EIGHT_EXISTING")
	private Boolean conductorTypeEightExisting;
	
	@Column(name="CONDUCTOR_SIZE_EIGHT_EXISTING")
	private Boolean conductorSizeEightExisting;
	
	@Column(name="CONDUIT_TYPE_EIGHT_EXISTING")
	private Boolean conduitTypeEightExisting;
	
	@Column(name="CONDUIT_SIZE_EIGHT_EXISTING")
	private Boolean conduitSizeEightExisting;
	
	@Column(name="CONDUCTOR_TYPE_NINE_EXISTING")
	private Boolean conductorTypeNineExisting;
	
	@Column(name="CONDUCTOR_SIZE_NINE_EXISTING")
	private Boolean conductorSizeNineExisting;
	
	@Column(name="CONDUIT_TYPE_NINE_EXISTING")
	private Boolean conduitTypeNineExisting;
	
	@Column(name="CONDUIT_SIZE_NINE_EXISTING")
	private Boolean conduitSizeNineExisting;
	
	@Column(name="CONDUCTOR_TYPE_NINE_TWO_EXISTING")
	private Boolean conductorTypeNineTwoExisting;
	
	@Column(name="CONDUCTOR_SIZE_NINE_TWO_EXISTING")
	private Boolean conductorSizeNineTwoExisting;
	
	@Column(name="CONDUIT_TYPE_NINE_TWO_EXISTING")
	private Boolean conduitTypeNineTwoExisting;
	
	@Column(name="CONDUIT_SIZE_NINE_TWO_EXISTING")
	private Boolean conduitSizeNineTwoExisting;
	
	@Column(name="CONDUCTOR_TYPE_TEN_EXISTING")
	private Boolean conductorTypeTenExisting;
	
	@Column(name="CONDUCTOR_SIZE_TEN_EXISTING")
	private Boolean conductorSizeTenExisting;
	
	@Column(name="CONDUIT_TYPE_TEN_EXISTING")
	private Boolean conduitTypeTenExisting;
	
	@Column(name="CONDUIT_SIZE_TEN_EXISTING")
	private Boolean conduitSizeTenExisting;
	
	@Column(name="CONDUCTOR_TYPE_ELEVEN_EXISTING")
	private Boolean conductorTypeElevenExisting;
	
	@Column(name="CONDUCTOR_SIZE_ELEVEN_EXISTING")
	private Boolean conductorSizeElevenExisting;
	
	@Column(name="CONDUIT_TYPE_ELEVEN_EXISTING")
	private Boolean conduitTypeElevenExisting;
	
	@Column(name="CONDUIT_SIZE_ELEVEN_EXISTING")
	private Boolean conduitSizeElevenExisting;
	
	@Column(name="CONDUCTOR_NEUTRAL")
	private Boolean conductorNeutral;
	
	@Column(name="CONDUCTOR_NEUTRAL_TWO")
	private Boolean conductorNeutralTwo;
	
	@Column(name="CONDUCTOR_NEUTRAL_THREE")
	private Boolean conductorNeutralThree;
	
	@Column(name="CONDUCTOR_NEUTRAL_FOUR")
	private Boolean conductorNeutralFour;
	
	@Column(name="CONDUCTOR_NEUTRAL_FIVE")
	private Boolean conductorNeutralFive;
	
	@Column(name="CONDUCTOR_NEUTRAL_SIX")
	private Boolean conductorNeutralSix;
	
	@Column(name="CONDUCTOR_NEUTRAL_SEVEN")
	private Boolean conductorNeutralSeven;
	
	@Column(name="CONDUCTOR_NEUTRAL_EIGHT")
	private Boolean conductorNeutralEight;
	
	@Column(name="CONDUCTOR_NEUTRAL_NINE")
	private Boolean conductorNeutralNine;
	
	@Column(name="CONDUCTOR_NEUTRAL_NINE_TWO")
	private Boolean conductorNeutralNineTwo;
	
	@Column(name="CONDUCTOR_NEUTRAL_TEN")
	private Boolean conductorNeutralTen;
	
	@Column(name="CONDUCTOR_NEUTRAL_ELEVEN")
	private Boolean conductorNeutralEleven;
	
	@Column(name="QTY_SEGMENT_TWELVE")
	private Integer qtySegmentTwelve;
	
	@Column(name="CONDUCTOR_TYPE_TWELVE_EXISTING")
	private Boolean conductorTypeTwelveExisting;
	
	@Column(name="CONDUCTOR_SIZE_TWELVE_EXISTING")
	private Boolean conductorSizeTwelveExisting;
	
	@Column(name="CONDUIT_TYPE_TWELVE_EXISTING")
	private Boolean conduitTypeTwelveExisting;
	
	@Column(name="CONDUIT_SIZE_TWELVE_EXISTING")
	private Boolean conduitSizeTwelveExisting;
	
	@Column(name="CONDUCTOR_QTY_TWELVE_OTHER")
	private Integer conductorQtyTwelveOther;
	
	@Column(name="CONDUCTOR_NEUTRAL_TWELVE")
	private Boolean conductorNeutralTwelve;
	
	@Column(name="CONDUCTOR_QTY_TWELVE")
	private String conductorQtyTwelve;
	
	@Column(name="CONDUCTOR_TYPE_TWELVE")
	private String conductorTypeTwelve;
	
	@Column(name="CONDUCTOR_SIZE_TWELVE")
	private String conductorSizeTwelve;
	
	@Column(name="CONDUIT_TYPE_TWELVE")
	private String conduitTypeTwelve;
	
	@Column(name="CONDUIT_SIZE_TWELVE")
	private String conduitSizeTwelve;
	
	@Column(name="CONDUCTOR_TYPE_TWELVE_OTHER")
	private String conductorTypeTwelveOther;
	
	@Column(name="CONDUCTOR_SIZE_TWELVE_OTHER")
	private String conductorSizeTwelveOther;
	
	@Column(name="CONDUIT_TYPE_TWELVE_OTHER")
	private String conduitTypeTwelveOther;
	
	@Column(name="CONDUIT_SIZE_TWELVE_OTHER")
	private String conduitSizeTwelveOther;
	

    /* CR-3227-MOD-001 */
	@Column
	private String circuitEnvironment;
	
	@Column
	private String circuitEnvironmentTwo;
	
	@Column
	private String circuitEnvironmentThree;
	
	@Column
	private String circuitEnvironmentFour;
	
	@Column
	private String circuitEnvironmentFive;
	
	@Column
	private String circuitEnvironmentSix;
	
	@Column
	private String circuitEnvironmentSeven;
	
	@Column
	private String circuitEnvironmentEight;
	
	@Column
	private String circuitEnvironmentNine;
	
	@Column
	private String circuitEnvironmentNineTwo;
	
	@Column
	private String circuitEnvironmentTen;
	
	@Column
	private String circuitEnvironmentEleven;
	
	@Column
	private String circuitEnvironmentTwelve;
    /* CR-3227-MOD-003 */
	
	@Column
	private Integer circuitLengthOne;
	
	@Column
	private Integer circuitLengthTwo;
	
	@Column
	private Integer circuitLengthThree;
	
	@Column
	private Integer circuitLengthFour;
	
	@Column
	private Integer circuitLengthFive;
	
	@Column
	private Integer circuitLengthSix;
	
	@Column
	private Integer circuitLengthSeven;
	
	@Column
	private Integer circuitLengthEight;
	
	@Column
	private Integer circuitLengthNine;
	
	@Column
	private Integer circuitLengthNineTwo;
	
	@Column
	private Integer circuitLengthTen;
	
	@Column
	private Integer circuitLengthEleven;
	
	@Column
	private Integer circuitLengthTwelve;

}
