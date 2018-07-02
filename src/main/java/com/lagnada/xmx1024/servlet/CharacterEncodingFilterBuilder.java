package com.lagnada.xmx1024.servlet;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

public class CharacterEncodingFilterBuilder {

    private CharacterEncodingFilterBuilder()
    {
    }

    public static CharacterEncodingFilterBuilder newCharacterEncodingFilterBuilder()
    {
        return new CharacterEncodingFilterBuilder();
    }

    public Filter build()
    {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
