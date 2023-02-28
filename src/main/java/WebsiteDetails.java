public class WebsiteDetails {

    public WebsiteDetailsClass setWebsiteDetails(String rootUrl, String regex, String[] dateSelector, String[] titleSelector, String[] contentSelectors, int dateParser) {
        WebsiteDetailsClass site = new WebsiteDetailsClass();
        site.rootUrl = rootUrl;
        site.regex = regex;
        site.dateSelector = dateSelector;
        site.titleSelector = titleSelector;
        site.contentSelectors = contentSelectors;
        site.dateParser = dateParser;
        return site;
    }

    WebsiteDetailsClass site1 = setWebsiteDetails(
            "https://blog.acthompson.net",
            "^((http[s]?):\\/)?\\/?((\\/blog\\.acthompson\\.net)*\\/)([\\d]{4}\\/)([\\d]{2}\\/)(.*)\\.(html)$",
            new String[]{"h2.date-header"},
            new String[]{"h3.post-title"},
            new String[]{"div.post-body"},
            1);

    WebsiteDetailsClass site2 = setWebsiteDetails(
            "https://computinged.wordpress.com",
            "^((http[s]?):\\/)?\\/?((\\/computinged\\.wordpress\\.com)*\\/)([\\d]{4}\\/)([\\d]{2}\\/)([\\d]{2}\\/)([^\\/]*)\\/$",
            new String[]{"em.date"},
            new String[]{"[id^=post-]", "h2"},
            new String[]{"[id^=post-]", "p:not(.info)", "p:not(#filedunder)"},
            2);

    WebsiteDetailsClass site3 = setWebsiteDetails(
            "https://freedom-to-tinker.com",
            "^((http[s]?):\\/)?\\/?((\\/freedom-to-tinker\\.com)*\\/)([\\d]{4}\\/)([\\d]{2}\\/)([\\d]{2}\\/)([^\\/]*)\\/$",
            new String[]{"span.date"},
            new String[]{"h1.entry-title"},
            new String[]{"div.entry-content"},
            3);

    WebsiteDetailsClass site4 = setWebsiteDetails(
            "https://scottaaronson.blog",
            "^((http[s]?):\\/)?\\/?((\\/scottaaronson\\.blog)*\\/)(\\?p=([\\d]{4}))$",
            new String[]{"p.postmetadata", "small", ":not(a)"},
            new String[]{"div.post", "h2"},
            new String[]{"div.entry"},
            4);

    WebsiteDetailsClass site5 = setWebsiteDetails(
            "https://www.section.io/blog/",
//            genSite5Urls(),
            "^((http[s]?):\\/)?\\/?((\\/www\\.section\\.io)*\\/)(blog\\/)([^\\/]*)\\/$",
            new String[]{"script:containsData(datePublished)"},
            new String[]{"h1.title-2"},
            new String[]{"div.section-rich-text-leading"},
            5);

    WebsiteDetailsClass site6 = setWebsiteDetails(
            "https://app.podscribe.ai/series/2017",
            "/^((http[s]?):\\/)?\\/?((\\/app\\.podscribe\\.ai)*\\/episode\\/)([\\d]+)$/",
            new String[]{""},
            new String[]{""},
            new String[]{"div[data-paragraph-text]"},
            6);

    WebsiteDetailsClass site7 = setWebsiteDetails(
            "https://the-stack-overflow-podcast.simplecast.com/episodes/",
            "^((http[s]?):\\/)?\\/?((\\/the-stack-overflow-podcast\\.simplecast\\.com)*\\/)(episodes\\/)(.*)(\\/transcript)$",
            new String[]{""},
            new String[]{""},
            new String[]{""},
            7);

    public WebsiteDetailsClass[] getAllWebsiteDetails() {
        return new WebsiteDetailsClass[]{site1, site2, site3, site4, site5};
    }

}