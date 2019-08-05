package com.al.boxipark_visitor.MainMenu;

public class MainMenuStyles extends MenuActivity {

    public static void sideMenuStyle() {
        jUserName.setTypeface(fontsSet.Book(context));
        jUserName.setTextSize((ScreenSize()));

        jProfileView.setTypeface(fontsSet.Book(context));
        jProfileView.setTextSize((float) (ScreenSize() * 0.65));

        jCardNumSide.setTypeface(fontsSet.Book(context));
        jCardNumSide.setTextSize((float) (ScreenSize() * 0.8));

        jRegNumberHolder.setTypeface(fontsSet.Book(context));
        jRegNumberHolder.setTextSize((float) (ScreenSize() * 0.8));

        jSupport.setTypeface(fontsSet.Book(context));
        jRegNumber.setTextSize((float) (ScreenSize() * 0.8));

        jCardNumHolder.setTypeface(fontsSet.Book(context));
        jCardNumHolder.setTextSize((float) (ScreenSize() * 0.8));

        jSupportHolder.setTypeface(fontsSet.Book(context));
        jSupportHolder.setTextSize((float) (ScreenSize() * 0.8));

        jRegNumber.setTypeface(fontsSet.Book(context));
        jSupport.setTextSize((float) (ScreenSize() * 0.8));

        jLogout.setTypeface(fontsSet.Book(context));
        jLogout.setTextSize((float) (ScreenSize() * 0.8));

        jPointsHolder.setTypeface(fontsSet.Medium(context));
        jPointsHolder.setTextSize((float) (ScreenSize() * 1.5));

        jPointsLabel.setTypeface(fontsSet.Book(context));
        jPointsLabel.setTextSize((float) (ScreenSize() * 0.8));

        jHistory.setTypeface(fontsSet.Book(context));
        jHistory.setTextSize((float) (ScreenSize() * 0.8));
    }

    public static void toolbarWeatherStyle() {
        float ration = ratio.ratio(context);

        jWeatherHead.setTextSize((float) (ScreenSize() * 0.85));
        jWeatherHead.setTypeface(fontsSet.Medium(context));
        jTemperature.setTextSize(ScreenSize() * 2);
        jTemperature.setTypeface(fontsSet.Book(context));
        jLocation.setTextSize((float) (ScreenSize() * 0.75));
        jLocation.setTypeface(fontsSet.Book(context));
        jOffer.setTextSize((float) (ScreenSize() * 0.5));
        jOffer.setTypeface(fontsSet.Book(context));
        jMoreInfo.setTypeface(fontsSet.Book(context));
        jMoreInfo.setTextSize((float) (screenSize.size(context) * 0.6));

        if (ration >= 1.3 && ration <= 1.5) {
            jHeadMain.getLayoutParams().height = (int) (ScreenSize() * 20);
            jWeatherHead.setTextSize((float) (ScreenSize() * 1.4));
            jTemperature.setTextSize(ScreenSize() * 3);
            jLocation.setTextSize((float) (ScreenSize() * 1.2));
            jOffer.setTextSize((float) (ScreenSize() * 0.6));
            jMapFrame.getLayoutParams().height = size * 5;
            jMoreInfo.setTextSize((float) (screenSize.size(context) * 0.8));

        }
    }

    public static void mapCompStyle() {
        size = (int) ScreenSize();

        jMapFrame.getLayoutParams().height = size * 6;

        jMap.setTypeface(fontsSet.Book(context));
        jMap.setPadding((int) ScreenSize() * 2, (int) ((int) ScreenSize() * 0.3), (int) ScreenSize() * 2, (int) ((int) ScreenSize() * 0.3));
        jMap.setTextSize((float) (ScreenSize() * 0.7));

    }
}
