package cc.catalysts.liferay.angular.portlet.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a list item fetched via REST.
 */
@Getter
@Setter
public class ListItem {
    int id;
    String text;

    public ListItem(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
