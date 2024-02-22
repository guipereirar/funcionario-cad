package br.edu.senaisp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import br.edu.senaisp.dao.FuncionarioDAO;
import br.edu.senaisp.model.Funcionario;

public class FuncionarioView extends JFrame {
	
	private JButton btnNovo = new JButton("Novo");
	private JLabel lblLista = new JLabel("Lista de Funcionários:");
	private JTextArea txtLista = new JTextArea();
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public FuncionarioView() {
		inicializar();
		acoes();
		
		FuncionarioDAO dao = new FuncionarioDAO();
		funcionarios = dao.lerFuncionarios();
		
		preencherFuncionarios();
	}
	
	public void inicializar() {
		this.setLayout(null);
		btnNovo.setBounds(50, 30, 150, 20);
		lblLista.setBounds(50, 70, 300, 20);
		txtLista.setBounds(50, 90, 500, 160);

		this.getContentPane().add(btnNovo);
		this.getContentPane().add(lblLista);
		this.getContentPane().add(txtLista);

		this.setSize(600, 350);
		this.setVisible(true);
	}
	
	private void acoes() {
		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FuncionarioDetView frm = new FuncionarioDetView();
				frm.show();

				Funcionario tmp = frm.getFuncionario();
				if (tmp != null) {
					funcionarios.add(tmp);
				}

				FuncionarioDAO dao = new FuncionarioDAO();
				try {
					dao.gravarFuncionarios(funcionarios);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frm, e1.getMessage());
				}
				preencherFuncionarios();
			}
		});
	}
	
	private void preencherFuncionarios() {
		txtLista.setText("");
		for (Funcionario funcionario : funcionarios) {
			txtLista.append(funcionario.getNome() + "[" + funcionario.getCpf() + "]");
			txtLista.append("\n");
		}

	}
	
}
