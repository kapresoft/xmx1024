package com.lagnada.xmx1024.controller.w8;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class W8Model
{
    @Valid
    private PartOneModel partOne = new PartOneModel();
    private PartTwoModel partTwo = new PartTwoModel();
    private PartThreeModel partThree = new PartThreeModel();

    private String password;

    public W8Model()
    {
        super();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public PartOneModel getPartOne()
    {
        return partOne;
    }

    public void setPartOne(PartOneModel partOne)
    {
        this.partOne = partOne;
    }

    public PartTwoModel getPartTwo()
    {
        return partTwo;
    }

    public void setPartTwo(PartTwoModel partTwo)
    {
        this.partTwo = partTwo;
    }

    public PartThreeModel getPartThree()
    {
        return partThree;
    }

    public void setPartThree(PartThreeModel partThree)
    {
        this.partThree = partThree;
    }

    private static <E extends Enum<E>> List<E> stringValuesToEnum(List<String> stringValues, Class<E> enumType)
    {
        List<E> enumList = new ArrayList<E>();
        if (stringValues != null && !stringValues.isEmpty())
        {
            enumList = new ArrayList<E>(stringValues.size());
            for (String value : stringValues)
            {
                if (value != null)
                {
                    enumList.add(Enum.valueOf(enumType, value));
                }
            }
        }
        return enumList;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public enum BeneficialOwnerType
    {
        INDIVIDUAL,
        CORPORATION,
        DISREGARDED_ENTITY,
        PARTNERSHIP,
        SIMPLE_TRUST, GRANTOR_TRUST, COMPLEX_TRUST,
        ESTATE,
        GOVERNMENT,
        INTERNATIONAL_ORGANIZATION,
        CENTRAL_BANK_OF_ISSUE,
        TAX_EXEMPT_ORGANIZATION,
        PRIVATE_FOUNDATION
    }

    public enum UnitedStatesTaxIdNumberType
    {
        SSN_OR_ITIN, EIN;
    }

    public static class PartOneModel
    {
        @NotBlank
        private String beneficialOwnerName;
        @NotBlank
        private String incorporationCountry;
        private BeneficialOwnerType beneficialOwnerType;
        @Valid
        private AddressModel permanentResidenceAddress = new AddressModel();
        private AddressModel mailingAddress = new AddressModel();

        private String taxIdNumber;
        private UnitedStatesTaxIdNumberType taxIdNumberType;
        private String foreignTaxIdNumber;
        private String referenceNumbers;

        public PartOneModel()
        {
        }

        public String getBeneficialOwnerName()
        {
            return beneficialOwnerName;
        }

        public void setBeneficialOwnerName(String beneficialOwnerName)
        {
            this.beneficialOwnerName = beneficialOwnerName;
        }

        public String getIncorporationCountry()
        {
            return incorporationCountry;
        }

        public void setIncorporationCountry(String incorporationCountry)
        {
            this.incorporationCountry = incorporationCountry;
        }

        public BeneficialOwnerType getBeneficialOwnerType()
        {
            return beneficialOwnerType;
        }

        public void setBeneficialOwnerType(BeneficialOwnerType beneficialOwnerType)
        {
            this.beneficialOwnerType = beneficialOwnerType;
        }

        public AddressModel getPermanentResidenceAddress()
        {
            return permanentResidenceAddress;
        }

        public void setPermanentResidenceAddress(AddressModel permanentResidenceAddress)
        {
            this.permanentResidenceAddress = permanentResidenceAddress;
        }

        public AddressModel getMailingAddress()
        {
            return mailingAddress;
        }


        public void setMailingAddress(AddressModel mailingAddress)
        {
            this.mailingAddress = mailingAddress;
        }

        public String getTaxIdNumber()
        {
            return taxIdNumber;
        }

        public void setTaxIdNumber(String taxIdNumber)
        {
            this.taxIdNumber = taxIdNumber;
        }

        public UnitedStatesTaxIdNumberType getTaxIdNumberType()
        {
            return taxIdNumberType;
        }

        public void setTaxIdNumberType(UnitedStatesTaxIdNumberType taxIdNumberType)
        {
            this.taxIdNumberType = taxIdNumberType;
        }

        public String getForeignTaxIdNumber()
        {
            return foreignTaxIdNumber;
        }

        public void setForeignTaxIdNumber(String foreignTaxIdNumber)
        {
            this.foreignTaxIdNumber = foreignTaxIdNumber;
        }

        public String getReferenceNumbers()
        {
            return referenceNumbers;
        }

        public void setReferenceNumbers(String referenceNumbers)
        {
            this.referenceNumbers = referenceNumbers;
        }
    }

    public static class AddressModel
    {
        @NotBlank
        private String streetAddress;
        private String city;
        private String stateOrProvince;
        private String postalCode;
        private String country;

        public AddressModel()
        {
        }

        public String getStreetAddress()
        {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress)
        {
            this.streetAddress = streetAddress;
        }

        public String getCity()
        {
            return city;
        }

        public void setCity(String city)
        {
            this.city = city;
        }

        public String getStateOrProvince()
        {
            return stateOrProvince;
        }

        public void setStateOrProvince(String stateOrProvince)
        {
            this.stateOrProvince = stateOrProvince;
        }

        public String getPostalCode()
        {
            return postalCode;
        }

        public void setPostalCode(String postalCode)
        {
            this.postalCode = postalCode;
        }

        public String getCountry()
        {
            return country;
        }

        public void setCountry(String country)
        {
            this.country = country;
        }
    }

    public enum TaxTreatyCertification
    {
        BENEFICIAL_OWNER_IS_A_RESIDENT_OF,
        US_TAX_ID_STATED_ON_LINE_6,
        BENEFICIAL_OWNER_IS_NOT_AN_INDIVIDUAL_MEETS_LIMITATION_PROVISION,
        BENEFICIAL_OWNER_IS_NOT_AN_INDIVIDUAL_MEETS_RESIDENT_STATUS,
        BENEFICIAL_OWNER_IS_RELATED_RELATED_WILL_FILE_8833_FORM
    }

    public static class PartTwoModel
    {
        private String beneficialOwnerResidence;
        List<String> taxTreatyCertifications = new ArrayList<String>(5);
        private String claimedArticle;
        private String withholdingPercentage;
        private String typeOfIncomeWithheld;
        private String treatyArticleReasons;

        public PartTwoModel()
        {
        }

        public String getBeneficialOwnerResidence()
        {
            return beneficialOwnerResidence;
        }

        public void setBeneficialOwnerResidence(String beneficialOwnerResidence)
        {
            this.beneficialOwnerResidence = beneficialOwnerResidence;
        }

        public List<String> getTaxTreatyCertifications()
        {
            return taxTreatyCertifications;
        }

        public void setTaxTreatyCertifications(List<String> taxTreatyCertifications)
        {
            this.taxTreatyCertifications = taxTreatyCertifications;
        }

        public List<TaxTreatyCertification> getTaxTreatyCertificationsAsEnum()
        {
            return stringValuesToEnum(taxTreatyCertifications, TaxTreatyCertification.class);
        }

        public String getClaimedArticle()
        {
            return claimedArticle;
        }

        public void setClaimedArticle(String claimedArticle)
        {
            this.claimedArticle = claimedArticle;
        }

        public String getWithholdingPercentage()
        {
            return withholdingPercentage;
        }

        public void setWithholdingPercentage(String withholdingPercentage)
        {
            this.withholdingPercentage = withholdingPercentage;
        }

        public String getTypeOfIncomeWithheld()
        {
            return typeOfIncomeWithheld;
        }

        public void setTypeOfIncomeWithheld(String typeOfIncomeWithheld)
        {
            this.typeOfIncomeWithheld = typeOfIncomeWithheld;
        }

        public String getTreatyArticleReasons()
        {
            return treatyArticleReasons;
        }

        public void setTreatyArticleReasons(String treatyArticleReasons)
        {
            this.treatyArticleReasons = treatyArticleReasons;
        }
    }

    public static class PartThreeModel
    {

        private boolean provideAndUpdateStatement;
        private String capacityInWhichActing;

        public PartThreeModel()
        {
        }

        public boolean isProvideAndUpdateStatement()
        {
            return provideAndUpdateStatement;
        }

        public void setProvideAndUpdateStatement(boolean provideAndUpdateStatement)
        {
            this.provideAndUpdateStatement = provideAndUpdateStatement;
        }

        public String getCapacityInWhichActing()
        {
            return capacityInWhichActing;
        }

        public void setCapacityInWhichActing(String capacityInWhichActing)
        {
            this.capacityInWhichActing = capacityInWhichActing;
        }
    }

}
