package cc.catalysts.liferay.angular.portlet.controller;

import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import cc.catalysts.liferay.angular.portlet.model.ListItem;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.util.PortalUtil;

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

        return "index";
    }

    @ResourceMapping("list")
    public void list(ResourceResponse response) throws Exception {
        List<ListItem> list = Arrays.asList(
            new ListItem(1, "First Item"),
            new ListItem(2, "Second Item"),
            new ListItem(3, "Third Item")
        );

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
}
