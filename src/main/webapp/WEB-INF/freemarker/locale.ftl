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

<h3 class="well well-small">Quick Select</h3>

<div class="btn-group" style="margin-bottom: 20px;">
    <button data-locale="en_US" class="locale-button btn btn-primary wide">United States</button>
    <button data-locale="fi_FI" class="locale-button btn wide">Finland</button>
    <button data-locale="en_IE" class="locale-button btn wide">Ireland</button>
    <button data-locale="it_IT" class="locale-button btn wide">Italy</button>
    <button data-locale="sv_SE" class="locale-button btn wide">Sweden</button>
    <button data-locale="en_GB" class="locale-button btn wide" style="margin-right: 10px;">Great Britain</button>
    <div class="btn-group">
        <button class="btn dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a data-locale="zh_CN" class="locale-button" href="#">China</a></li>
            <li><a data-locale="ko_KO" class="locale-button" href="#">Korea</a></li>
            <li><a data-locale="ru_RU" class="locale-button" href="#">Russia</a></li>
            <li><a data-locale="vi_VN" class="locale-button" href="#">Vietnam</a></li>
        </ul>
    </div>
</div>

    <#assign ds="$"/>
<h3 class="well well-small">Current Locale</h3>

<div style="margin-bottom: 20px">
    <div class="row">
        <div class="span4"><h4>${ds}{requestLocale}</h4></div>
        <div class="span2"><h4>${ds}{.locale}</h4></div>
        <div class="span2"><h4>${ds}{.lang}</h4></div>
    </div>
    <div class="row">
        <div class="span1">${requestLocale?string}</div>
        <div class="span3">${requestLocale.displayName}</div>
        <div class="span2">${.locale}</div>
        <div class="span2">${.lang}</div>
    </div>
</div>

<h3 class="well well-small">DateFormat Patterns</h3>

<div>
    <div class="row">
        <div class="span3"><h4 class="label">DateFormat.SHORT (${.locale})</h4></div>
        <div class="span3"><h4 class="label">DateFormat.MEDIUM (${.locale})</h4></div>
        <div class="span3"><h4 class="label">DateFormat.LONG (${.locale})</h4></div>
    </div>
    <div class="row">
        <div class="span2">${shortDatePattern!""}</div>
        <div class="span2">${shortDate!""}</div>
        <div class="span2">${mediumDatePattern!""}</div>
        <div class="span2">${mediumDate!""}</div>
        <div class="span2">${longDatePattern!""}</div>
        <div class="span2">${longDate!""}</div>
    </div>
</div>

<div>
    <div class="row">
        <div class="span3"><h4 class="label">DateFormat.SHORT (U.S.)</h4></div>
        <div class="span3"><h4 class="label">DateFormat.MEDIUM (U.S.)</h4></div>
        <div class="span3"><h4 class="label">DateFormat.LONG (U.S.)</h4></div>
    </div>
    <div class="row">
        <div class="span2">${unitedStatesShortDatePattern!""}</div>
        <div class="span2">${unitedStatesShortDate!""}</div>
        <div class="span2">${unitedStatesMediumDatePattern!""}</div>
        <div class="span2">${unitedStatesMediumDate!""}</div>
        <div class="span2">${unitedStatesLongDatePattern!""}</div>
        <div class="span2">${unitedStatesLongDate!""}</div>
    </div>
</div>

<h3 class="well well-small">Currency Info</h3>

<div>
    <div class="row">
        <div class="span2"><h4 class="label">Currency (${.locale})</h4></div>
        <div class="span10"><h4 class="label">Currency Symbol (${.locale})</h4></div>
    </div>
    <div class="row">
        <div class="span2">${currency!""}</div>
        <div class="span10">${currencySymbol!""}</div>
    </div>
</div>

<div>
    <div class="row">
        <div class="span2"><h4 class="label">Currency (U.S.)</h4></div>
        <div class="span10"><h4 class="label">Currency Symbol (U.S.)</h4></div>
    </div>
    <div class="row">
        <div class="span2">${unitedStatesCurrency!""}</div>
        <div class="span10">${unitedStatesCurrencySymbol!""}</div>
    </div>
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
</div>
</@locale>

<script>
    $(document).ready(function () {
        attachButtonListener = function (localeEl) {
            $(localeEl).click(function () {
                window.location = '?locale=' + $(this).data('locale');
            });
        };
        $('[class~="locale-button"]').each(function () {
            attachButtonListener($(this));
        });
    });
</script>

</body>
</html>


