package cc.catalysts.liferay.angular.portlet.controller;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.util.PortalUtil;

import cc.catalysts.liferay.angular.portlet.model.ListItem;

/**
 * The PortletController containing view & rest requests.
 */
@Component
@RequestMapping("view")
public class PortletController {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @RenderMapping
    public String view(RenderResponse response, ModelMap model) {

        ResourceURL baseResourceUrl = response.createResourceURL();
        model.addAttribute("ajaxUrl", baseResourceUrl.toString() + "&p_p_resource_id=");
        model.addAttribute("staticContentUrl", getStaticContentUrl());
        model.addAttribute("standalone", false);
        model.addAttribute("jsFiles", getJavascriptFiles());

        return "index";
    }

    @ResourceMapping("list")
    public void list(ResourceResponse response) throws Exception {
        List<ListItem> list = Arrays.asList(new ListItem(1, "First Item"), new ListItem(2, "Second Item"),
                new ListItem(3, "Third Item"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSON_MAPPER.writeValue(response.getPortletOutputStream(), list);
    }

    private String getStaticContentUrl() {
        PortletRequest portletRequest = (PortletRequest) RequestContextHolder.currentRequestAttributes()
                .getAttribute("javax.portlet.request", 0);
        return PortalUtil.getStaticResourceURL(PortalUtil.getHttpServletRequest(portletRequest),
                portletRequest.getContextPath() + "/{filename}");
    }

    private LinkedHashSet<String> getJavascriptFiles() {
        File classFolder = new File(this.getClass().getResource("/").getFile());
        File distFolder = new File(classFolder.getParentFile().getParent() + "/dist");

        LinkedHashSet<String> jsFiles = new LinkedHashSet<String>();
        String[] inlineBundle = distFolder.list(new WildcardFileFilter("inline*.js"));
        if (inlineBundle != null && inlineBundle.length > 0) {
            jsFiles.add(inlineBundle[0]);
        }
        String[] vendorBundle = distFolder.list(new WildcardFileFilter("vendor*.js"));
        if (vendorBundle != null && vendorBundle.length > 0) {
            jsFiles.add(vendorBundle[0]);
        }
        String[] mainBundle = distFolder.list(new WildcardFileFilter("main*.js"));
        if (mainBundle != null && mainBundle.length > 0) {
            jsFiles.add(mainBundle[0]);
        }

        return jsFiles;
    }
}
