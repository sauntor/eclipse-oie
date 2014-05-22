package org.sauntor.oie.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class OieAction implements IObjectActionDelegate {

	private Shell shell;
	private IResource resource;
	
	/**
	 * Constructor for Action1.
	 */
	public OieAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			File file = resource.getLocation().toFile();
			if(file.isFile()) {
				file = file.getParentFile();
			}
			Runtime.getRuntime().exec("explorer " + file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if(selection.isEmpty()) {
			action.setEnabled(false);
			resource = null;
			return;
		}
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection elements = (IStructuredSelection) selection;
			resource = (IResource) elements.getFirstElement();
		}
	}

}
