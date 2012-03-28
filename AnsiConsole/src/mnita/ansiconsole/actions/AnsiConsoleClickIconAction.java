package mnita.ansiconsole.actions;

import mnita.ansiconsole.AnsiConsoleActivator;
import mnita.ansiconsole.preferences.AnsiConsolePreferenceConstants;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class AnsiConsoleClickIconAction implements IViewActionDelegate {

    @Override
    public void run(IAction action) {
        boolean isAnsiconEnabled = AnsiConsoleActivator.getDefault().getPreferenceStore().getBoolean(AnsiConsolePreferenceConstants.PREF_ANSI_CONSOLE_ENABLED);
        isAnsiconEnabled = ! isAnsiconEnabled;
        AnsiConsoleActivator.getDefault().getPreferenceStore().setValue(AnsiConsolePreferenceConstants.PREF_ANSI_CONSOLE_ENABLED, isAnsiconEnabled);

        String currentImage = action.getImageDescriptor().toString();
        boolean isCurrentIconEnabled = true;
        if( currentImage.indexOf("ansiconsole.gif") == -1 )
            isCurrentIconEnabled = false;

        if( isCurrentIconEnabled ^ isAnsiconEnabled ) { // current status does not match current icon, swap icons
            ImageDescriptor imgDesc = action.getDisabledImageDescriptor();
            action.setDisabledImageDescriptor(action.getImageDescriptor());
            action.setImageDescriptor(imgDesc);
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }

    @Override
    public void init(IViewPart view) {
    }

}
