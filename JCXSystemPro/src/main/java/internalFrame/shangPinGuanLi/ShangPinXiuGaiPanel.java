﻿package internalFrame.shangPinGuanLi;
import internalFrame.guanli.Item;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TbGysinfo;
import model.TbSpinfo;

import com.lzw.dao.Dao;
public class ShangPinXiuGaiPanel extends JPanel {
	private JComboBox gysQuanCheng;
	private JTextField beiZhu;
	private JTextField wenHao;
	private JTextField piHao;
	private JTextField baoZhuang;
	private JTextField guiGe;
	private JTextField danWei;
	private JTextField chanDi;
	private JTextField jianCheng;
	private JTextField quanCheng;
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox sp;
	public ShangPinXiuGaiPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);

		setupComponet(new JLabel("商品名称："), 0, 0, 1, 1, false);
		quanCheng = new JTextField();
		quanCheng.setEditable(false);
		setupComponet(quanCheng, 1, 0, 3, 1, true);

		setupComponet(new JLabel("简称："), 0, 1, 1, 1, false);
		jianCheng = new JTextField();
		setupComponet(jianCheng, 1, 1, 3, 10, true);

		setupComponet(new JLabel("产地："), 0, 2, 1, 1, false);
		chanDi = new JTextField();
		setupComponet(chanDi, 1, 2, 3, 300, true);

		setupComponet(new JLabel("单位："), 0, 3, 1, 1, false);
		danWei = new JTextField();
		setupComponet(danWei, 1, 3, 1, 130, true);

		setupComponet(new JLabel("规格："), 2, 3, 1, 1, false);
		guiGe = new JTextField();
		setupComponet(guiGe, 3, 3, 1, 1, true);

		setupComponet(new JLabel("包装："), 0, 4, 1, 1, false);
		baoZhuang = new JTextField();
		setupComponet(baoZhuang, 1, 4, 1, 1, true);

		setupComponet(new JLabel("批号："), 2, 4, 1, 1, false);
		piHao = new JTextField();
		setupComponet(piHao, 3, 4, 1, 1, true);

		setupComponet(new JLabel("批准文号："), 0, 5, 1, 1, false);
		wenHao = new JTextField();
		setupComponet(wenHao, 1, 5, 3, 1, true);

		setupComponet(new JLabel("供应商全称："), 0, 6, 1, 1, false);
		gysQuanCheng = new JComboBox();
		gysQuanCheng.setMaximumRowCount(5);
		setupComponet(gysQuanCheng, 1, 6, 3, 1, true);

		setupComponet(new JLabel("备注："), 0, 7, 1, 1, false);
		beiZhu = new JTextField();
		setupComponet(beiZhu, 1, 7, 3, 1, true);

		setupComponet(new JLabel("选择商品"), 0, 8, 1, 0, false);
		sp = new JComboBox();
		sp.setPreferredSize(new Dimension(230, 21));
		// 处理客户信息的下拉选择框的选择事件
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSpSelectAction();
			}
		});
		// 定位商品信息的下拉选择框
		setupComponet(sp, 1, 8, 2, 0, true);
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 8, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						ShangPinXiuGaiPanel.this, "确认删除商品信息吗？");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = Dao.delete("delete tb_spinfo where id='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
								"商品：" + item.getName() + "。删除成功");
						sp.removeItem(item);
					}
				}
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				TbSpinfo spInfo = new TbSpinfo();
				spInfo.setId(item.getId());
				spInfo.setBz(baoZhuang.getText().trim());
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setDw(danWei.getText().trim());
				spInfo.setGg(guiGe.getText().trim());
				spInfo.setGysname(gysQuanCheng.getSelectedItem().toString()
						.trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setMemo(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setPzwh(wenHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				if (Dao.updateSp(spInfo) == 1)
					JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
							"修改完成");
				else
					JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
							"修改失败");
			}
		});
	}
	// 初始化商品下拉选择框
	public void initComboBox() {
		List khInfo = Dao.getSpInfos();
		List<Item> items = new ArrayList<Item>();
		sp.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			sp.addItem(item);
		}
		doSpSelectAction();
	}
	// 初始化供应商下拉选择框
	public void initGysBox() {
		List gysInfo = Dao.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gysQuanCheng.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gysQuanCheng.addItem(item);
		}
		doSpSelectAction();
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	// 处理商品选择事件
	private void doSpSelectAction() {
		Item selectedItem;
		if (!(sp.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) sp.getSelectedItem();
		TbSpinfo spInfo = Dao.getSpInfo(selectedItem);
		if (!spInfo.getId().isEmpty()) {
			quanCheng.setText(spInfo.getSpname());
			baoZhuang.setText(spInfo.getBz());
			chanDi.setText(spInfo.getCd());
			danWei.setText(spInfo.getDw());
			guiGe.setText(spInfo.getGg());
			jianCheng.setText(spInfo.getJc());
			beiZhu.setText(spInfo.getMemo());
			piHao.setText(spInfo.getPh());
			wenHao.setText(spInfo.getPzwh());
			beiZhu.setText(spInfo.getMemo());
			// 设置供应商下拉框的当前选择项
			Item item = new Item();
			item.setId(null);
			item.setName(spInfo.getGysname());
			TbGysinfo gysInfo = Dao.getGysInfo(item);
			item.setId(gysInfo.getId());
			item.setName(gysInfo.getName());
			for (int i = 0; i < gysQuanCheng.getItemCount(); i++) {
				Item gys = (Item) gysQuanCheng.getItemAt(i);
				if (gys.getName().equals(item.getName())) {
					item = gys;
				}
			}
			gysQuanCheng.setSelectedItem(item);
		}
	}

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
				{
			         public void run()
			         {
			        	 JFrame frame = new JFrame();
			     		 frame.setBounds(100, 100, 800, 600); 
			     		 desktopPane = new JDesktopPane();
			    		 frame.getContentPane().add(desktopPane);
			    		 JPanel panel = new ShangPinXiuGaiPanel();
			    		 desktopPane.add(panel);
			    		 panel.setVisible(true);
			        	 frame.setVisible(true);
			         }
		             JDesktopPane desktopPane;
				});
	}
}
