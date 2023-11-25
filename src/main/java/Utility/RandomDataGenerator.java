package Utility;

import java.time.LocalDate;
import java.time.Month;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

	static Faker faker = new Faker();

	public static String getRandomDataFor(RandomDataTypesName dataTypesName) {
		switch(dataTypesName)
		{

			case FIRSTNAME:
					return faker.name().firstName();

			case LASTNAME:
				return faker.name().lastName();

			case FULLNAME:
				return faker.name().fullName();

			case COUNTRYNAME:
				return faker.country().name();

			case CITYNAME:
				return faker.address().cityName();


			case STATENAME:
				return faker.address().state();

			case ADDRESS:
				return faker.address().fullAddress();

			default:
				return "";

		}
	}

	public static String gerRandomNumber(int count) {
		return faker.number().digits(count);
	}

	public static String gerRandomMobileNumber() {
		return faker.phoneNumber().cellPhone();
	}

	public static String gerRandomAlphabets(int minLengthInclusive,int maxLengthExclusive) {
		return RandomStringUtils.randomAlphabetic(minLengthInclusive, maxLengthExclusive);
	}

	public static String gerRandomURL() {
		return faker.internet().url();
	}

	public static int gerCurrentYear() {
		return LocalDate.now().getYear();
	}

	public static Month gerCurrentMonth() {
		return LocalDate.now().getMonth();
	}

	public static int gerCurrentDayDate() {
		return LocalDate.now().getDayOfMonth();
	}
}
