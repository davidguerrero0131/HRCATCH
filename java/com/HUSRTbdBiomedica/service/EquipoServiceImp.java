package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;
import com.HUSRTbdBiomedica.app.Dao.IEquipoDao;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class EquipoServiceImp implements IEquipoService{

	@Autowired
	private IEquipoDao EquipoDao;
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Equipo> ListEquipo() {		
		return (List<Equipo>)EquipoDao.findAll();
	}
	
	@Override
	public List<Equipo> ListEquiposServicio(Long idServicio) {
		
		return EquipoDao.getEquiposServicio(idServicio);
	}

	
	@Override
	@Transactional
	public int ContarEquipos(){
		return EquipoDao.countAll();
	}
	@Override
	@Transactional
	public int ContarEquiposCuatrimestral() {
		return EquipoDao.countCuatrimestral();
	}
	@Override
	@Transactional
	public int ContarEquiposTrimestral() {
		return EquipoDao.countTrimestral();
	}
	@Override
	@Transactional
	public int ContarEquiposSemestral() {
		return EquipoDao.countSemestral();
	}
	@Override
	@Transactional
	public int ContarEquiposAnual() {
		return EquipoDao.countAnual();
	}
	
	@Override
	@Transactional(readOnly= true)
	public List<Equipo> findEquiposAnual(Long id){
		return (List<Equipo>)EquipoDao.findEquiposAnual(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findEquiposSemestral(Long id){
		return (List<Equipo>)EquipoDao.findEquiposSemestral(id);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findEquiposTrimestral(Long id){
		return (List<Equipo>)EquipoDao.findEquiposTrimestral(id);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findEquiposCuatrimestral(Long id){
		return (List<Equipo>)EquipoDao.findEquiposCuatrimestral(id);
	}

	@Override
	@Transactional
	public Optional<Equipo> ListEquipobyId(Long id) {
		return null;
	}

	@Override
	@Transactional
	public Equipo findOne(Long id) {
		return EquipoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Equipo equipo) {
		EquipoDao.save(equipo);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EquipoDao.delete(findOne(id));
	}


	@Override
	public int numprevEnero() {
		return EquipoDao.numprevenero();
	}


	@Override
	public int numpreFebrero() {
		return EquipoDao.numprefebrero();
	}


	@Override
	public int numpreMarzo() {
		return EquipoDao.numpremarzo();
	}


	@Override
	public int numpreAbril() {
		return EquipoDao.numpreabril();
	}


	@Override
	public int numpreMayo() {
		return EquipoDao.numpremayo();
	}


	@Override
	public int numpreJunio() {
		return EquipoDao.numprejunio();
	}


	@Override
	public int numpreJulio() {
		return EquipoDao.numprejulio();
	}


	@Override
	public int numpreAgosto() {
		return EquipoDao.numpreagosto();
	}


	@Override
	public int numpreSeptiembre() {
		return EquipoDao.numpreseptiembre();
	}


	@Override
	public int numpreOctubre() {
		return EquipoDao.numpreoctubre();
	}


	@Override
	public int numpreNoviembre() {
		return EquipoDao.numprenoviembre();
	}


	@Override
	public int numpreDiciembre() {
		return EquipoDao.numprediciembre();
	}


	@Override
	public List<Equipo> prevEnero() {
		return EquipoDao.prevenero();
	}


	@Override
	public List<Equipo> preFebrero() {
		return EquipoDao.prefebrero();
	}


	@Override
	public List<Equipo> preMarzo() {
		return EquipoDao.premarzo();
	}


	@Override
	public List<Equipo> preAbril() {
		return EquipoDao.preabril();
	}


	@Override
	public List<Equipo> preMayo() {
		return EquipoDao.premayo();
	}


	@Override
	public List<Equipo> preJunio() {
		return EquipoDao.prejunio();
	}


	@Override
	public List<Equipo> preJulio() {
		return EquipoDao.prejulio();
	}


	@Override
	public List<Equipo> preAgosto() {
		return EquipoDao.preagosto();
	}


	@Override
	public List<Equipo> preSeptiembre() {
		return EquipoDao.preseptiembre();
	}


	@Override
	public List<Equipo> preOctubre() {
		return EquipoDao.preoctubre();
	}


	@Override
	public List<Equipo> preNoviembre() {
		return EquipoDao.prenoviembre();
	}


	@Override
	public List<Equipo> preDiciembre() {
		return EquipoDao.prediciembre();
	}


	@Override
	public int numgarantiavEnero() {
		return EquipoDao.numgarantiavenero();
	}


	@Override
	public int numgarantiaFebrero() {
		return EquipoDao.numgarantiafebrero();
	}


	@Override
	public int numgarantiaMarzo() {
		return EquipoDao.numgarantiamarzo();
	}


	@Override
	public int numgarantiaAbril() {
		return EquipoDao.numgarantiaabril();
	}


	@Override
	public int numgarantiaMayo() {
		return EquipoDao.numgarantiamayo();
	}


	@Override
	public int numgarantiaJunio() {
		return EquipoDao.numgarantiajunio();
	}


	@Override
	public int numgarantiaJulio() {
		return EquipoDao.numgarantiajulio();
	}


	@Override
	public int numgarantiaAgosto() {
		return EquipoDao.numgarantiaagosto();
	}


	@Override
	public int numgarantiaSeptiembre() {
		return EquipoDao.numgarantiaseptiembre();
	}


	@Override
	public int numgarantiaOctubre() {
		return EquipoDao.numgarantiaoctubre();
	}


	@Override
	public int numgarantiaNoviembre() {
		return EquipoDao.numgarantianoviembre();
	}


	@Override
	public int numgarantiaDiciembre() {
		return EquipoDao.numgarantiadiciembre();
	}


	@Override
	public List<Equipo> garantiavEnero() {
		return EquipoDao.garantiavenero();
	}


	@Override
	public List<Equipo> garantiaFebrero() {
		return EquipoDao.garantiafebrero();
	}


	@Override
	public List<Equipo> garantiaMarzo() {
		return EquipoDao.garantiamarzo();
	}


	@Override
	public List<Equipo> garantiaAbril() {
		return EquipoDao.garantiaabril();
	}


	@Override
	public List<Equipo> garantiaMayo() {
		return EquipoDao.garantiamayo();
	}


	@Override
	public List<Equipo> garantiaJunio() {
		return EquipoDao.garantiajunio();
	}


	@Override
	public List<Equipo> garantiaJulio() {
		return EquipoDao.garantiajulio();
	}


	@Override
	public List<Equipo> garantiaAgosto() {
		return EquipoDao.garantiaagosto();
	}


	@Override
	public List<Equipo> garantiaSeptiembre() {
		return EquipoDao.garantiaseptiembre();
	}


	@Override
	public List<Equipo> garantiaOctubre() {
		return EquipoDao.garantiaoctubre();
	}


	@Override
	public List<Equipo> garantiaNoviembre() {
		return EquipoDao.garantianoviembre();
	}


	@Override
	public List<Equipo> garantiaDiciembre() {
		return EquipoDao.garantiadiciembre();
	}


	@Override
	public int numcontratovEnero() {
		return EquipoDao.numcontratovenero();
	}


	@Override
	public int numcontratoFebrero() {
		return EquipoDao.numcontratofebrero();
	}


	@Override
	public int numcontratoMarzo() {
		return EquipoDao.numcontratomarzo();
	}


	@Override
	public int numcontratoAbril() {
		return EquipoDao.numcontratoabril();
	}


	@Override
	public int numcontratoMayo() {
		return EquipoDao.numcontratomayo();
	}


	@Override
	public int numcontratoJunio() {
		return EquipoDao.numcontratojunio();
	}


	@Override
	public int numcontratoJulio() {
		return EquipoDao.numcontratojulio();
	}


	@Override
	public int numcontratoAgosto() {
		return EquipoDao.numcontratoagosto();
	}


	@Override
	public int numcontratoSeptiembre() {
		return EquipoDao.numcontratoseptiembre();
	}


	@Override
	public int numcontratoOctubre() {
		return EquipoDao.numcontratooctubre();
	}


	@Override
	public int numcontratoNoviembre() {
		return EquipoDao.numcontratonoviembre();
	}


	@Override
	public int numcontratoDiciembre() {
		return EquipoDao.numcontratodiciembre();
	}


	@Override
	public List<Equipo> contratovEnero() {
		return EquipoDao.contratovenero();
	}


	@Override
	public List<Equipo> contratoFebrero() {
		return EquipoDao.contratofebrero();
	}


	@Override
	public List<Equipo> contratoMarzo() {
		return EquipoDao.contratomarzo();
	}


	@Override
	public List<Equipo> contratoAbril() {
		return EquipoDao.contratoabril();
	}


	@Override
	public List<Equipo> contratoMayo() {
		return EquipoDao.contratomayo();
	}


	@Override
	public List<Equipo> contratoJunio() {
		return EquipoDao.contratojunio();
	}


	@Override
	public List<Equipo> contratoJulio() {
		return EquipoDao.contratojulio();
	}


	@Override
	public List<Equipo> contratoAgosto() {
		return EquipoDao.contratoagosto();
	}


	@Override
	public List<Equipo> contratoSeptiembre() {
		return EquipoDao.contratoseptiembre();
	}


	@Override
	public List<Equipo> contratoOctubre() {
		return EquipoDao.contratooctubre();
	}


	@Override
	public List<Equipo> contratoNoviembre() {
		return EquipoDao.contratonoviembre();
	}


	@Override
	public List<Equipo> contratoDiciembre() {
		return EquipoDao.contratodiciembre();
	}


	@Override
	public List<String> preeneroname() {
		return EquipoDao.prenameenero();
	}


	@Override
	public List<String> prefebreroname() {
		return EquipoDao.prenamefebrero();
	}


	@Override
	public List<String> premarzoname() {
		return EquipoDao.prenamemarzo();
	}


	@Override
	public List<String> preabrilname() {
		return EquipoDao.prenameabril();
	}


	@Override
	public List<String> premayoname() {
		return EquipoDao.prenamemayo();
	}


	@Override
	public List<String> prejunioname() {
		return EquipoDao.prenamejunio();
	}


	@Override
	public List<String> prejulioname() {
		return EquipoDao.prenamejulio();
	}


	@Override
	public List<String> preagostoname() {
		return EquipoDao.prenameagosto();
	}


	@Override
	public List<String> preseptiembrename() {
		return EquipoDao.prenameseptiembre();
	}


	@Override
	public List<String> preoctubrename() {
		return EquipoDao.prenameoctubre();
	}


	@Override
	public List<String> prenoviembrename() {
		return EquipoDao.prenamenoviembre();
	}


	@Override
	public List<String> prediciembrename() {
		return EquipoDao.prenamediciembre();
	}


	@Override
	public List<String> coneneroname() {
		return EquipoDao.contratomeenero();
	}


	@Override
	public List<String> confebreroname() {
		return EquipoDao.contratomefebrero();
	}


	@Override
	public List<String> conmarzoname() {
		return EquipoDao.contratomemarzo();
	}


	@Override
	public List<String> conabrilname() {
		return EquipoDao.contratomeabril();
	}


	@Override
	public List<String> conmayoname() {
		return EquipoDao.contratomemayo();
	}


	@Override
	public List<String> conjunioname() {
		return EquipoDao.contratomejunio();
	}


	@Override
	public List<String> conjulioname() {
		return EquipoDao.contratomejulio();
	}


	@Override
	public List<String> conagostoname() {
		return EquipoDao.contratomeagosto();
	}


	@Override
	public List<String> conseptiembrename() {
		return EquipoDao.contratomeseptiembre();
	}


	@Override
	public List<String> conoctubrename() {
		return EquipoDao.contratomeoctubre();
	}


	@Override
	public List<String> connoviembrename() {
		return EquipoDao.contratomenoviembre();
	}


	@Override
	public List<String> condiciembrename() {
		return EquipoDao.contratomediciembre();
	}


	@Override
	public List<String> gareneroname() {
		return EquipoDao.garantiameenero();
	}


	@Override
	public List<String> garfebreroname() {
		return EquipoDao.garantiamefebrero();
	}


	@Override
	public List<String> garmarzoname() {
		return EquipoDao.garantiamemarzo();
	}


	@Override
	public List<String> garabrilname() {
		return EquipoDao.garantiameabril();
	}


	@Override
	public List<String> garmayoname() {
		return EquipoDao.garantiamemayo();
	}


	@Override
	public List<String> garjunioname() {
		return EquipoDao.garantiamejunio();
	}


	@Override
	public List<String> garjulioname() {
		return EquipoDao.garantiamejulio();
	}


	@Override
	public List<String> garagostoname() {
		return EquipoDao.garantiameagosto();
	}


	@Override
	public List<String> garseptiembrename() {
		return EquipoDao.garantiameseptiembre();
	}


	@Override
	public List<String> garoctubrename() {
		return EquipoDao.garantiameoctubre();
	}


	@Override
	public List<String> garnoviembrename() {
		return EquipoDao.garantiamenoviembre();
	}


	@Override
	public List<String> gardiciembrename() {
		return EquipoDao.garantiamediciembre();
	}


	@Override
	public List<String> listarseries() {
		return EquipoDao.listaseries();
	}


	@Override
	public List<Equipo> findequipobySerie(String serie) {
		return EquipoDao.findEquipobySerie(serie);
	}


	@Override
	public List<Equipo> findEquiposInactivos() {
		return EquipoDao.findEquiposinactivos();
	}


	@Override
	public List<Integer> countequiposbytipo() {
	
		return EquipoDao.countbyTiposEquipo();
	}


	@Override
	public List<Equipo> equiposcalval() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposcalval();
	}


	@Override
	public List<String> equiposcalvalstr() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposcalvalstr();
	}


	@Override
	public List<Equipo> equiposCV() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposcv();
	}


	@Override
	public List<Equipo> listequipostoplan() {
		// TODO Auto-generated method stub
		return EquipoDao.findEquipostoPlanning();
	}


	@Override
	public List<Equipo> listbyrespobable(Long id) {
		// TODO Auto-generated method stub
		return EquipoDao.findEquiposbyResponsable(id);
	}


	@Override
	public List<String> groupbyNMM() {
		// TODO Auto-generated method stub
		return EquipoDao.groupbynmm();
	}


	@Override
	public List<String> groupbyGarantia() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposGarantia();
	}


	@Override
	public List<String> groupbyContrato() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposContrato();
	}


	@Override
	public List<String> groupbyGyC() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposSinCyG();
	}


	@Override
	public List<Equipo> listEquiposByMM(String marca, String modelo) {
		// TODO Auto-generated method stub
		return EquipoDao.findEquiposByMM(marca,modelo);
	}


	@Override
	public List<Equipo> listGarantias() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposcompleteGarantia();
	}


	@Override
	public List<Equipo> listContratos() {
		// TODO Auto-generated method stub
		return EquipoDao.equiposcompleteContrato();
	}

	@Override
	public void bajaEquipo(Long idEquipo) {
		EquipoDao.bajaEquipo(idEquipo);
	}


	@Override
	public List<Equipo> getAllequiposF() {
		
		return EquipoDao.getAllEquiposF();
	}


	@Override
	public List<Equipo> getComodatosPorTipoEquipo(long idTipoEquipo) {
		// TODO Auto-generated method stub
		return EquipoDao.getComodatosPorTipoEquipo(idTipoEquipo);
	}


	@Override
	public List<Equipo> getComodatosPorServicio(long idServicio) {
		// TODO Auto-generated method stub
		return EquipoDao.getComodatosPorServicio(idServicio);
	}


	@Override
	public List<Equipo> getAllEquiposSinComodatos() {
		// TODO Auto-generated method stub
		return EquipoDao.getAllEquiposSinComodatos();
	}


	@Override
	public List<Equipo> getAllComodatos() {
		// TODO Auto-generated method stub
		return EquipoDao.getAllComodatos();
	}


	@Override
	public List<Equipo> getAllEquiposT() {
		// TODO Auto-generated method stub
		return EquipoDao.getAllEquiposT();
	}

	@Override
	public List<Equipo> getEquiposResponsableComodato(Long id) {
		
		return EquipoDao.getEquiposComodatoResponsable(id);
	}

}
