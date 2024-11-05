package com.PlayGroundAdv.Solar.service.utils;
/*
 * @author Arij
 */

import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CheckValueTypesService {

	public boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean isNumeric(String str) {
		try {
			return str != null && str.matches("-?\\d+(\\.\\d+)?");
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isNumericNotZero(String str) {
		try {
			return str.matches("-?\\d+(\\.\\d+)?") && Float.parseFloat(str) > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isNumericPositive(Integer v) {
		try {
			return v != null && v > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isLongPositive(Long v) {
		try {
			return v != null && v > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isFloatPositive(Float v) {
		try {
			return v != null && v > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isStringNotEmpty(String str) {
		try {
			return str != null && !str.isEmpty() && !str.trim().isEmpty();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean compare(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	public boolean compareBoolean(Boolean bl1, Boolean bl2) {
		return (bl1 == null ? bl2 == null || Boolean.FALSE.equals(bl2)
				: bl1.equals(bl2) || (bl2 == null && Boolean.FALSE.equals(bl1)));
	}

	public boolean Equals(Object str1, Object str2) {
		try {
			return str1 != null && str2 != null && str1.equals(str2);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean EqualsCaseInsensitive(String str1, String str2) {
		try {
			return str1 != null && str2 != null && str1.equalsIgnoreCase(str2);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean NotEquals(Object str1, Object str2) {
		try {
			return str1 != null && str2 != null && !str1.equals(str2);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean NotEqualsNull(Object str1, Object str2) {
		try {
			return (str1 != null && str2 == null) || (str1 == null && str2 != null)
					|| (str1 != null && str2 != null && !str1.equals(str2));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean notContains(String str1, CharSequence str2) {
		try {
			return str1 != null && str2 != null && !str1.contains(str2);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean contains(String str1, CharSequence str2) {
		try {
			return str1 != null && str2 != null && str1.contains(str2);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean containsCaseInsensitive(String str1, String str2) {
		try {
			return str1 != null && str2 != null && str1.toLowerCase().contains(str2.toLowerCase());
		} catch (Exception e) {
			return false;
		}
	}

	public Float getFloatValue(String str1) {
		try {
			return Float.valueOf(str1);
		} catch (Exception e) {
			return 0f;
		}
	}

	public Float getMinFloat(float f1, float f2) {
		try {
			return !isFloatPositive(f1) && !isFloatPositive(f2) ? 0f
					: !isFloatPositive(f1) ? f2 : !isFloatPositive(f2) ? f1 : Math.min(f2, f1);
		} catch (Exception e) {
			return 0f;
		}
	}

	public boolean notChecked(Boolean checked) {
		try {
			return checked == null || Boolean.FALSE.equals(checked);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public Long convertToLong(Object o) {
		String stringToConvert = String.valueOf(o);
		Long convertedLong = Long.parseLong(stringToConvert);
		return convertedLong;

	}

	public Integer convertToInteger(String str) {
		return isNumericNotZero(str) ? Integer.valueOf(str) : 0;

	}

	public String convert(String s) {
		return s == null || Equals(s, "null") ? "" : s;
	}

	public String trim(String s) {
		return !isStringNotEmpty(s) ? null : s.trim();
	}

	public String trimLower(String s) {
		return !isStringNotEmpty(s) ? null : s.trim().toLowerCase();
	}

	public String removeSpecialChar(String s) {
		return isStringNotEmpty(s)
				? s.replace("/", "").replace(":", "").replace("*", "").replace("?", "").replace("\"", "")
						.replace("<", "").replace(">", "").replace("|", "")
				: s;
	}
	
	public String getNumericValue(String s) {
		return isStringNotEmpty(s)
				? s.replaceAll("[^0-9]", "")
				: s;
	}

	public String decimalFormat(Object s) {
		return String.valueOf(new DecimalFormat("##.##").format(s));
	}
}
