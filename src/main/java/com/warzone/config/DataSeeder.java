package com.warzone.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warzone.base.MilitaryBase;
import com.warzone.base.BaseRepository;
import com.warzone.conflict.Conflict;
import com.warzone.conflict.ConflictRepository;
import com.warzone.military.MilitaryPower;
import com.warzone.military.MilitaryRepository;
import com.warzone.nuclear.NuclearArsenal;
import com.warzone.nuclear.NuclearRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final MilitaryRepository militaryRepo;
    private final NuclearRepository nuclearRepo;
    private final BaseRepository baseRepo;
    private final ConflictRepository conflictRepo;
    private final ObjectMapper mapper;

    public DataSeeder(MilitaryRepository militaryRepo, NuclearRepository nuclearRepo,
                      BaseRepository baseRepo, ConflictRepository conflictRepo, ObjectMapper mapper) {
        this.militaryRepo = militaryRepo;
        this.nuclearRepo = nuclearRepo;
        this.baseRepo = baseRepo;
        this.conflictRepo = conflictRepo;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        seedMilitary();
        seedNuclear();
        seedBases();
        seedConflicts();
        log.info("All seed data loaded successfully");
    }

    private void seedMilitary() {
        if (militaryRepo.count() > 0) { log.info("Military data exists, skipping"); return; }
        try {
            InputStream in = new ClassPathResource("data/military-power.json").getInputStream();
            List<MilitaryPower> list = mapper.readValue(in, new TypeReference<>() {});
            militaryRepo.saveAll(list);
            log.info("Seeded {} military powers", list.size());
        } catch (Exception e) { log.error("Military seed failed: {}", e.getMessage()); }
    }

    private void seedNuclear() {
        if (nuclearRepo.count() > 0) { log.info("Nuclear data exists, skipping"); return; }
        try {
            InputStream in = new ClassPathResource("data/nuclear-arsenals.json").getInputStream();
            List<NuclearArsenal> list = mapper.readValue(in, new TypeReference<>() {});
            nuclearRepo.saveAll(list);
            log.info("Seeded {} nuclear nations", list.size());
        } catch (Exception e) { log.error("Nuclear seed failed: {}", e.getMessage()); }
    }

    private void seedBases() {
        if (baseRepo.count() > 0) { log.info("Base data exists, skipping"); return; }
        try {
            InputStream in = new ClassPathResource("data/military-bases.json").getInputStream();
            List<MilitaryBase> list = mapper.readValue(in, new TypeReference<>() {});
            baseRepo.saveAll(list);
            log.info("Seeded {} military bases", list.size());
        } catch (Exception e) { log.error("Base seed failed: {}", e.getMessage()); }
    }

    private void seedConflicts() {
        if (conflictRepo.count() > 0) { log.info("Conflict data exists, skipping"); return; }

        List<Conflict> list = List.of(
            mkConflict("Russia-Ukraine War","Full-scale invasion","RU","UA",48.37,31.16,"CRITICAL","ACTIVE_WAR","INTERSTATE",500000,8000000,LocalDate.of(2022,2,24)),
            mkConflict("Israel-Hamas War","Military operation in Gaza","IL","PS",31.35,34.30,"CRITICAL","ACTIVE_WAR","INTERSTATE",40000,1900000,LocalDate.of(2023,10,7)),
            mkConflict("Sudan Civil War","SAF vs RSF conflict","SD","SD",15.50,32.55,"HIGH","ACTIVE_WAR","CIVIL",15000,9000000,LocalDate.of(2023,4,15)),
            mkConflict("Myanmar Civil War","Junta vs resistance","MM","MM",19.76,96.07,"HIGH","ACTIVE_WAR","CIVIL",50000,2600000,LocalDate.of(2021,2,1)),
            mkConflict("China-Taiwan Tensions","Taiwan Strait standoff","CN","TW",23.69,120.96,"HIGH","TENSION","TERRITORIAL",0,0,LocalDate.of(2022,8,1)),
            mkConflict("India-Pakistan Kashmir","Border tensions","IN","PK",34.08,74.79,"MEDIUM","TENSION","BORDER",0,0,LocalDate.of(1947,10,22)),
            mkConflict("North Korea Nuclear Threat","Nuclear program","KP","KR",38.34,127.13,"HIGH","TENSION","INTERSTATE",0,0,LocalDate.of(2006,10,9)),
            mkConflict("Yemen Civil War","Houthi conflict","YE","YE",15.55,48.51,"HIGH","ACTIVE_WAR","CIVIL",377000,4000000,LocalDate.of(2014,9,21)),
            mkConflict("Somalia Insurgency","Al-Shabaab","SO","SO",2.04,45.31,"MEDIUM","ACTIVE_WAR","CIVIL",20000,3000000,LocalDate.of(2006,1,1)),
            mkConflict("Syria Conflict","Civil war aftermath","SY","SY",34.80,38.99,"MEDIUM","ACTIVE_WAR","CIVIL",500000,6700000,LocalDate.of(2011,3,15))
        );
        conflictRepo.saveAll(list);
        log.info("Seeded {} conflicts", list.size());
    }

    private Conflict mkConflict(String title, String desc, String a, String b, double lat, double lng,
                                 String sev, String status, String type, int cas, int disp, LocalDate start) {
        Conflict c = new Conflict();
        c.setTitle(title); c.setDescription(desc); c.setCountryA(a); c.setCountryB(b);
        c.setLat(lat); c.setLng(lng); c.setSeverity(sev); c.setStatus(status);
        c.setConflictType(type); c.setCasualties(cas); c.setDisplaced(disp);
        c.setStartedAt(start); c.setSource("SEED");
        return c;
    }
}
