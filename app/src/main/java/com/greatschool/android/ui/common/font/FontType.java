package com.greatschool.android.ui.common.font;

public enum FontType {

    AVENIR_BLACK("fonts/avenir_black.otf"),
    AVENIR_BOOK("fonts/avenir_book.otf"),
    AVENIR_ROMAN("fonts/avenir_roman.otf"),
    AVENIR_MED("fonts/brandon_med.otf"),
    AVENIR_REG("fonts/brandon_reg.otf"),
    LATO_BOLD("fonts/lato_bold.otf"),
    LATO_LIGHT("fonts/lato_light.otf"),
    LATO_REGULAR("fonts/lato_regular.otf"),
    ROBOTO_MEDIUM("fonts/roboto_medium.ttf");

    final String assetUrl;

    FontType(String assetUrl) {
        this.assetUrl = assetUrl;
    }

}
