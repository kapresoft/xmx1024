<!DOCTYPE html>
<html>
<head>
    <title>Locale Info</title>
    <meta name="activeMenu" content="localeMenu"/>
</head>

<body id="index" class="home">

<p class="lead">
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec diam justo, tristique ac augue vitae, convallis
    placerat turpis. Maecenas a sagittis sapien, id porta est. Fusce quis enim at neque elementum tempus. Pellentesque
    vel volutpat elit.
</p>


<#assign locale = "com.lagnada.xmx1024.freemarker.LocaleDirective"?new()/>

<@locale>
<h3>Current Locale</h3>
    <#assign ds="$"/>
<div>
    <div class="row">
        <div class="span3"><h4>${ds}{requestLocale}</h4></div>
        <div class="span2"><h4>${ds}{.locale}</h4></div>
        <div class="span2"><h4>${ds}{.lang}</h4></div>
    </div>
    <div class="row">
        <div class="span3">${requestLocale?string} ${requestLocale.displayName}</div>
        <div class="span2">${.locale}</div>
        <div class="span2">${.lang}</div>
    </div>
</div>


<div style="margin-left: 20px;"></div>

<h3>Quick Select</h3>

<div class="btn-group" style="margin-bottom: 20px;">
    <button id="us" data-locale="en_US" class="locale-button btn btn-primary wide">United States</button>
    <button id="finland" data-locale="fi_FI" class="locale-button btn wide">Finland</button>
    <button id="sweden" data-locale="sv_SE" class="locale-button btn wide">Sweden</button>
    <button id="sweden" data-locale="zh_CN" class="locale-button btn wide">China</button>
    <button id="sweden" data-locale="ko_KO" class="locale-button btn wide">Korea</button>
    <button id="sweden" data-locale="ru_RU" class="locale-button btn wide">Russia</button>
</div>

<h2>Available Locales:</h2>

<div style="margin-left: 20px; font-size: 1.1em;">
    <div class="row">
        <div class="span1"><h3 class="font-effect-wallpaper">Code</h3></div>
        <div class="span3"><h3 class="font-effect-wallpaper">Description</h3></div>
        <div class="span4"><h3 class="font-effect-wallpaper">Localized Description</h3></div>
    </div>
    <#list availableLocales as locale>
        <div class="row">
            <div class="span1"><a href="?locale=${locale.localeText}">${locale.localeText}</a></div>
            <div class="span3">${locale.displayName}</div>
            <div class="span4 text-success">${locale.localeDisplayName}</div>
        </div>
    </#list>
<#--<#list availableLocales as locale>-->
<#--<a href="?locale=${locale.localeText}">${locale.localeText}</a> = ${locale.localeDisplayName} [${locale.displayName}]</br>-->
<#--</#list>-->
</div>
</@locale>

<script>
    $(document).ready(function () {
        attachButtonListener = function (localeEl) {
            $(localeEl).click(function () {
                window.location = '?locale=' + $(this).data('locale');
            });
        };
        $('button[class~="locale-button"]').each(function () {
            attachButtonListener($(this));
        });
    });
</script>

</body>
</html>


