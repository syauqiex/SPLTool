// =======================================================================================
// Warning. Do not edit this file directly.  This file was built with the SPL generator.
// Eduardo Andrade - <%= Time.now %>
// =======================================================================================
package br.com.splgenerator.web.folhapagamento.processamentopagamento;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import br.com.splgenerator.audit.ILog;
import br.com.splgenerator.dao.ModRhDao;
import br.com.splgenerator.model.cadastro.Funcionario;
import br.com.splgenerator.monitoring.ModRhStats;

@ManagedBean
@SessionScoped
public class Bonus implements Serializable {

	private static final long serialVersionUID = 1L;

@ManagedProperty(value = "#{$VP-DAO}")
private ModRhDao dao;
@ManagedProperty(value = "#{$VP-AUDIT}")
private ILog log;

	private transient HtmlDataTable htmlDataTable;

	private br.com.splgenerator.model.cadastro.Funcionario _novoFuncionario;
	private br.com.splgenerator.model.cadastro.Funcionario _editarFuncionario;

	private int valorPagamento;

	public Bonus() {
		_novoFuncionario = new br.com.splgenerator.model.cadastro.Funcionario();
		_editarFuncionario = null;
	}

	public void setDao(ModRhDao dao) { this.dao = dao; }
	public void setLog(ILog log) { this.log = log; }

	public HtmlDataTable getHtmlDataTable() { return htmlDataTable; }
	public void setHtmlDataTable(HtmlDataTable htmlDataTable) { this.htmlDataTable = htmlDataTable; }

	public java.util.List<br.com.splgenerator.model.cadastro.Funcionario> getFuncionarios() {
		log.log("TRACE", "Executando getFuncionarios()...");
		ModRhStats.incrementCounterSelectFuncionarios();
		return dao.getFuncionarios();
	}

	public String efetuarPagamento() {
		log.log("TRACE", "Executando alterarPagamento()... Valor pagamento: " + valorPagamento);
		this._editarFuncionario = (Funcionario) htmlDataTable.getRowData();
		this._editarFuncionario.setBonus(valorPagamento);

		ModRhStats.incrementCounterUpdateFuncionarios();
		dao.atualizarFuncionario(_editarFuncionario);
		addFacesMessage("Funcionario atualizado com sucesso!");
		return "bonus";

	}

	public Funcionario getNovofuncionario() { return _novoFuncionario; }
	public void setNovofuncionario(Funcionario value) { _novoFuncionario = value; }

	public Funcionario getEditarfuncionario() { return _editarFuncionario; }
	public void setEditarfuncionario(Funcionario value) { _editarFuncionario = value; }

	private void addFacesMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	public int getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(int valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

}
