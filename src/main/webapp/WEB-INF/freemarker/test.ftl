<h2>Locale Info </h2>

<#assign locale = "com.lagnada.xmx1024.freemarker.LocaleDirective"?new()/>

<@locale>
<h3>Current Locale</h3>
<div style="margin-left: 20px;">${requestLocale.toString()} ${requestLocale.displayName}</div>

<h3>Available Locales:</h3>
<div style="margin-left: 20px;">
    <#list availableLocales as locale>
    ${locale.toString()} = ${locale.displayName}</br>
    </#list>
</div>
</@locale>

.locale: ${.locale}<br/>
.lang: ${.lang}<br/>

