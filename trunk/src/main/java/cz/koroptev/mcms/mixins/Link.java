package cz.koroptev.mcms.mixins;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.PageLink;

public class Link extends PageLink {

    @Parameter
    private Integer id;

    @Parameter
    private String backUrl;

    private EditCall call;

}
