package com.greatschool.android.ui.common.font;

public enum FontType {

    PROXIMA_BOLD("fonts/proxima_nova_bold.otf"),
    PROXIMA_SEMIBOLD("fonts/proxima_nova_semibold.otf"),
    PROXIMA_REGULAR("fonts/proxima_nova_regular.otf");

    final String assetUrl;

    FontType(String assetUrl) {
        this.assetUrl = assetUrl;
    }

}
