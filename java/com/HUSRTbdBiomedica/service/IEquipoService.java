package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;
import com.HUSRTbdBiomedica.app.entity.Equipo;

public interface IEquipoService {
	public List<Equipo>ListEquipo();
	public List<Equipo>ListEquiposServicio(Long idServicio);
	public Optional<Equipo>ListEquipobyId(Long id);
	public Equipo findOne(Long id);
	public void save(Equipo equipo);
	public void delete(Long id);
	public List<Integer> countequiposbytipo();
	
	public List<Equipo> getAllequiposF();
	public List<Equipo> getAllEquiposT();
	public List<Equipo> getAllEquiposSinComodatos();
	public List<Equipo> getAllComodatos();
	
	public List<Equipo> findEquiposAnual(Long id);
	public List<Equipo> findEquiposSemestral(Long id);
	public List<Equipo> findEquiposTrimestral(Long id);
	public List<Equipo> findEquiposCuatrimestral(Long id);
	
	public List<Equipo> getEquiposResponsableComodato(Long id);
	
	public int ContarEquipos();
	public int ContarEquiposCuatrimestral();
	public int ContarEquiposTrimestral();
	public int ContarEquiposSemestral();
	public int ContarEquiposAnual();
	
	//tojava
	public List<String> preeneroname();
	public List<String> prefebreroname();
	public List<String> premarzoname();
	public List<String> preabrilname();
	public List<String> premayoname();
	public List<String> prejunioname();
	public List<String> prejulioname();
	public List<String> preagostoname();
	public List<String> preseptiembrename();
	public List<String> preoctubrename();
	public List<String> prenoviembrename();
	public List<String> prediciembrename();
	
	public List<String> coneneroname();
	public List<String> confebreroname();
	public List<String> conmarzoname();
	public List<String> conabrilname();
	public List<String> conmayoname();
	public List<String> conjunioname();
	public List<String> conjulioname();
	public List<String> conagostoname();
	public List<String> conseptiembrename();
	public List<String> conoctubrename();
	public List<String> connoviembrename();
	public List<String> condiciembrename();
	
	public List<String> gareneroname();
	public List<String> garfebreroname();
	public List<String> garmarzoname();
	public List<String> garabrilname();
	public List<String> garmayoname();
	public List<String> garjunioname();
	public List<String> garjulioname();
	public List<String> garagostoname();
	public List<String> garseptiembrename();
	public List<String> garoctubrename();
	public List<String> garnoviembrename();
	public List<String> gardiciembrename();
	//preventivos
	public int numprevEnero();
	
	
	public int numpreFebrero();
	public int numpreMarzo();
	public int numpreAbril();
	public int numpreMayo();
	public int numpreJunio();
	public int numpreJulio();
	public int numpreAgosto();
	public int numpreSeptiembre();
	public int numpreOctubre();
	public int numpreNoviembre();
	public int numpreDiciembre();
	
	public List<Equipo> prevEnero();
	
	
	public List<Equipo> preFebrero();
	public List<Equipo> preMarzo();
	public List<Equipo> preAbril();
	public List<Equipo> preMayo();
	public List<Equipo> preJunio();
	public List<Equipo> preJulio();
	public List<Equipo> preAgosto();
	public List<Equipo> preSeptiembre();
	public List<Equipo> preOctubre();
	public List<Equipo> preNoviembre();
	public List<Equipo> preDiciembre();
	
	//garantias
	public int numgarantiavEnero();
	public int numgarantiaFebrero();
	public int numgarantiaMarzo();
	public int numgarantiaAbril();
	public int numgarantiaMayo();
	public int numgarantiaJunio();
	public int numgarantiaJulio();
	public int numgarantiaAgosto();
	public int numgarantiaSeptiembre();
	public int numgarantiaOctubre();
	public int numgarantiaNoviembre();
	public int numgarantiaDiciembre();
	
	public List<Equipo> garantiavEnero();
	public List<Equipo> garantiaFebrero();
	public List<Equipo> garantiaMarzo();
	public List<Equipo> garantiaAbril();
	public List<Equipo> garantiaMayo();
	public List<Equipo> garantiaJunio();
	public List<Equipo> garantiaJulio();
	public List<Equipo> garantiaAgosto();
	public List<Equipo> garantiaSeptiembre();
	public List<Equipo> garantiaOctubre();
	public List<Equipo> garantiaNoviembre();
	public List<Equipo> garantiaDiciembre();
	
	//contratos
	public int numcontratovEnero();
	public int numcontratoFebrero();
	public int numcontratoMarzo();
	public int numcontratoAbril();
	public int numcontratoMayo();
	public int numcontratoJunio();
	public int numcontratoJulio();
	public int numcontratoAgosto();
	public int numcontratoSeptiembre();
	public int numcontratoOctubre();
	public int numcontratoNoviembre();
	public int numcontratoDiciembre();
	
	public List<Equipo> contratovEnero();
	public List<Equipo> contratoFebrero();
	public List<Equipo> contratoMarzo();
	public List<Equipo> contratoAbril();
	public List<Equipo> contratoMayo();
	public List<Equipo> contratoJunio();
	public List<Equipo> contratoJulio();
	public List<Equipo> contratoAgosto();
	public List<Equipo> contratoSeptiembre();
	public List<Equipo> contratoOctubre();
	public List<Equipo> contratoNoviembre();
	public List<Equipo> contratoDiciembre();
	
	//calva
	public List<Equipo> equiposcalval();
	public List<String> equiposcalvalstr();
	public List<Equipo> equiposCV();
	
	//finbyserie
	public List<String> listarseries();
	public List<Equipo> findequipobySerie(String serie);
	//inactivos
	public List<Equipo> findEquiposInactivos();
	
	//planning
	public List<Equipo> listequipostoplan();
	
	public List<Equipo> listbyrespobable(Long id);
	public List<String> groupbyNMM();
	
	public List<String> groupbyGyC();
	public List<String> groupbyGarantia();
	public List<String> groupbyContrato();
	
	public List<Equipo> listEquiposByMM(String marca, String modelo);
	
	public List<Equipo> listGarantias();
	public List<Equipo> listContratos();
	
	public void bajaEquipo(Long idEquipo);
	
	public List<Equipo> getComodatosPorTipoEquipo(long idTipoEquipo);
	public List<Equipo> getComodatosPorServicio(long idServicio);
	
	
}
