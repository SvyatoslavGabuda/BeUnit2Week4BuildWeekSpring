package it.epicode.bw.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.epicode.bw.auth.entity.ERole;
import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.csvreader.CsvReader;
import it.epicode.bw.models.Comuni_italiani;
import it.epicode.bw.models.Provincia;
import it.epicode.bw.repository.ComuneRepo;
import it.epicode.bw.repository.ProvinceRepo;
import it.epicode.bw.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MainRunner implements ApplicationRunner {
@Autowired
ComuneRepo cRepo;
@Autowired
ProvinceRepo pRepo;
@Autowired
AuthServiceImpl aS;
@Autowired
IndirizzoService iS;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.warn("main runner start");
		String path1 = "C:/Users/Alessandro/eclipse-workspace/Epicode/BuildWeekF/BeUnit2Week4BuildWeekSpring/src/main/resources/datipercomuni/comuni-italiani.csv";
		String path2 = "C:/Users/Alessandro/eclipse-workspace/Epicode/BuildWeekF/BeUnit2Week4BuildWeekSpring/src/main/resources/datipercomuni/province-italiane.csv";
		CsvReader csv = new CsvReader();
		
		// IMPORT LISTA COMUNI
//		csv.setScanner(path1);
//		csv.getRecords().forEach(f->{
//			System.out.println(f);
//			Comuni_italiani c = new Comuni_italiani();
//			c.setCodice_Provincia(f.get(0));
//			c.setProgressivo_del_Comune(f.get(1));
//			c.setDenominazione_in_italiano(f.get(2));
//			c.setNomeProvincia(f.get(3));
//			cRepo.save(c);
//		});
		 //IMPORT LISTA PROVINCE
//		csv.setScanner(path2);
//		csv.getRecords().forEach(f->{
//			System.out.println(f);
//			Provincia p = new Provincia();
//			p.setSigla(f.get(0));
//			p.setProvincia(f.get(1));
//			p.setRegione(f.get(2));
//			pRepo.save(p);
//		});
//		aS.changePermissions(1, ERole.ROLE_ADMIN);
		
//		iS.creaIndirizzi();
		
	}

}
