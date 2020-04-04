package dev.anindya.helloworld.appdrawer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import android.graphics.drawable.Drawable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AppInfoTest {

    private AppInfo mAppInfo;
    @Mock
    private CharSequence mockLabel;
    @Mock
    private CharSequence mockAnotherLabel;
    @Mock
    private CharSequence mockPackageName;
    @Mock
    private Drawable mockDrawable;

    private static final String LABEL_STRING = "ABC";

    private static final String ANOTHER_LABEL_STRING = "DEF";

    /**
     * Set up the test.
     */
    @Before
    public void setUp() {
        initMocks(this);
        mAppInfo = new AppInfo(mockLabel, mockPackageName, mockDrawable);
        when(mockLabel.toString()).thenReturn(LABEL_STRING);
        when(mockAnotherLabel.toString()).thenReturn(ANOTHER_LABEL_STRING);
    }

    @Test
    public void getIcon() {
        assertEquals(mockDrawable, mAppInfo.getIcon());
    }

    @Test
    public void getPackageName() {
        assertEquals(mockPackageName, mAppInfo.getPackageName());
    }

    @Test
    public void compareTo() {
        final AppInfo anotherAppInfo = new AppInfo(mockAnotherLabel, mockPackageName, mockDrawable);
        assertTrue(mAppInfo.compareTo(anotherAppInfo) < 0);
    }
}