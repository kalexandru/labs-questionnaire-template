package com.engagepoint.controller;

import com.engagepoint.bean.GroupBean;
import com.engagepoint.bean.QuestionBean;
import com.engagepoint.bean.SectionBean;
import com.engagepoint.bean.TemplateBean;
import com.engagepoint.bean.*;

/**
 * Created with IntelliJ IDEA.
 * User: anton.kovunov
 * Date: 1/31/14
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@SessionScoped
public class QuestFormTreeController implements Serializable {

    private TreeNode root = new DefaultTreeNode("Root", null);
    private TreeNode selectedNode;
    private TemplateBean templateBean;

    public TemplateBean getTemplateBean() {
        return templateBean;
    }

    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
        setNodes();
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect() {
        System.out.println(selectedNode);
    }

    public void setNodes() {
        root = new DefaultTreeNode("Root", null);
        ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();

        // Iterator LEVEL_0 for filling sections of choosed template
        for (SectionBean sectionBean : templateBean.getSectionsList()) {
            TreeNode section = new DefaultTreeNode(sectionBean, root);

            // Iterator LEVEL_1 for filling groups of choosed section
            for(GroupBean groupBean : sectionBean.getGroupsList()) {
                TreeNode group = new DefaultTreeNode(groupBean, section);

                // Iterator LEVEL_2 for filling questions of choosed section
                for(QuestionBean questionBean : groupBean.getQuestionsList()) {
                    new DefaultTreeNode(questionBean, group);
                } // END of QUESTION Iterator
            } // END of GROUP Iterator
        } // END of SECTION Iterator

    } // END of setNodes() METHOD

    public TreeNode getRoot() {
        return root;
    }

    public String getPicture(String nodeID) {
        if (nodeID.startsWith("SOME")) {
            return "ui-icon-edit-small";
        } else {
            return "ui-icon-add-v2";
        }
    }

    public String editSelectedGroup() {
        QuestionEditController controller = new QuestionEditController();
        if (selectedNode != null && selectedNode.getData().toString().contains("Group")) {
            return controller.income();
        } else {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "You've chosen not a group or more than one group");
            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
        }
        return null;
    }

    public void delete() {
        selectedNode.getParent().getChildren().remove(selectedNode);
        if (!selectedNode.getParent().getData().equals("Root")) {
            ((BasicOperationWithBean) selectedNode.getParent().getData()).deleteFromInnerList(selectedNode.getData());
        } else {
            templateBean.deleteFromInnerList(selectedNode.getData());
        }
    }
}
