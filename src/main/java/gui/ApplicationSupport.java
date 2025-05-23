package gui;

// http://www.javaworld.com/javaworld/jw-05-2003/jw-0530-designpatterns-p2.html
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.*;
import javax.swing.*;

public final class ApplicationSupport {
   static private final String PREFS_BUNDLE_BASENAME = "gui/prefs";
   static private final String BUNDLE_BASENAME = "gui/app", PREFERRED_LOCALE_KEY = "locale";

   static private final JPanel statusArea = new JPanel();
   static private final JLabel status = new JLabel();
   static private ResourceBundle preferences, resources;
   static private Locale locale;

   static {
      try {
         preferences = ResourceBundle.getBundle(PREFS_BUNDLE_BASENAME);
         locale = new Locale(preferences.getString(PREFERRED_LOCALE_KEY));
      }
      catch(java.util.MissingResourceException ex) {
         System.err.println("ERROR: cannot find preferences properties file " + 
                            BUNDLE_BASENAME);
      }

      try {
         resources = ResourceBundle.getBundle(BUNDLE_BASENAME, locale);
      }
      catch(java.util.MissingResourceException ex) {
         System.err.println("ERROR: cannot find properties file for " + BUNDLE_BASENAME);
      }
   };

   // disallow direct instantiation
   private ApplicationSupport() {}
   
   public static void launch(final JFrame f, String title,
                       final int x, final int y, 
                       final int w, int h) {
      f.setTitle(title);
      f.setBounds(x,y,w,h);
      f.setVisible(true);
      f.setResizable(true);

      status.setHorizontalAlignment(JLabel.LEFT);

      statusArea.setBorder(BorderFactory.createEtchedBorder());
      statusArea.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
      statusArea.add(status);

      f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

      f.addWindowListener(new WindowAdapter() {
         public void windowClosed(WindowEvent e) {
            System.exit(0);
         }
      });
   }
   public static Locale getLocale() {
      return locale;
   }
   public static JMenu addMenu(final JFrame f, String titleKey,
                               String[] itemKeys) {

      JMenuBar mb = f.getJMenuBar();
      if(mb == null) {
         mb = new JMenuBar();
         f.setJMenuBar(mb);
      }

      JMenu menu = new JMenu(ApplicationSupport.getResource(titleKey));

      for(int i=0; i < itemKeys.length; ++i) {
         menu.add(new JMenuItem(ApplicationSupport.getResource(itemKeys[i])));
      }
      mb.add(menu);
      return menu;
   }
   public static JPanel getStatusArea() {
      return statusArea;
   }
   public static void showStatus(String s) {
      status.setText(s);
   }
   public static String getResource(String key) {
      return (resources == null) ? null : resources.getString(key);
   }
   public static String formatMessage(String patternKey, String[] params) {
      String pattern = ApplicationSupport.getResource(patternKey);
      MessageFormat fmt = new MessageFormat(pattern);
      return fmt.format(params);
   }
}
