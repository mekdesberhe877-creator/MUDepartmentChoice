package com.example.mudepartmentchoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mu.db";
    private static final int DATABASE_VERSION = 4;

    public DBHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE departments(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)");
        db.execSQL("CREATE TABLE result(id INTEGER PRIMARY KEY AUTOINCREMENT, best TEXT)");
        insertDepartments(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS departments");
        db.execSQL("DROP TABLE IF EXISTS result");
        onCreate(db);
    }

    private void insertDepartments(SQLiteDatabase db) {
        String[][] departments = {
                {"Accounting & Finance", "Focuses on recording and reporting financial transactions.\n\nCareers: Accountant, Auditor, Financial Analyst, Tax Consultant."},
                {"Management", "Planning and leading resources to achieve goals.\n\nCareers: Project Manager, HR Manager, Operations Manager, Business Consultant."},
                {"Marketing Management", "Application of marketing techniques and resource management.\n\nCareers: Marketing Manager, Brand Specialist, Market Researcher, PR Officer."},
                {"Economics", "Studies production, distribution, and consumption.\n\nCareers: Economist, Data Analyst, Policy Advisor, Investment Banker."},
                {"Business Administration", "Management of business operations and decision-making.\n\nCareers: Business Administrator, Entrepreneur, CEO, Office Manager."},
                {"Agricultural Economics", "Applying economic principles to food and fiber production.\n\nCareers: Agribusiness Manager, Agricultural Policy Analyst, Farm Consultant."},
                {"Animal Science", "Biology of animals under human control.\n\nCareers: Animal Breeder, Livestock Manager, Veterinary Technician, Zoo Keeper."},
                {"Crop Production", "Science and art of growing plants for food and fuel.\n\nCareers: Agronomist, Crop Consultant, Plant Breeder, Farm Manager."},
                {"Horticulture", "Culture of plants for food, comfort, and beauty.\n\nCareers: Landscape Designer, Greenhouse Manager, Floriculturist, Orchard Manager."},
                {"Natural Resources Management", "Management of land, water, soil, and wildlife.\n\nCareers: Conservationist, Environmental Consultant, Park Ranger, Forestry Manager."},
                {"Rural Development", "Improving life and well-being in rural areas.\n\nCareers: Community Development Officer, NGO Coordinator, Rural Planner."},
                {"Soil & Water Management", "Protection and enhancement of soil and water resources.\n\nCareers: Soil Scientist, Hydrologist, Irrigation Engineer, Environmental Technician."},
                {"Wildlife & Range Management", "Management of wildlife and natural habitats.\n\nCareers: Wildlife Biologist, Range Manager, Conservation Officer, Ecologist."},
                {"Medicine", "Diagnosis, treatment, and prevention of disease.\n\nCareers: Doctor, Surgeon, Specialist Physician, Medical Researcher."},
                {"Nursing", "Care of individuals and families to recover health.\n\nCareers: Registered Nurse, Nurse Practitioner, Clinical Specialist, Health Educator."},
                {"Pharmacy", "Preparing, dispensing, and reviewing drugs.\n\nCareers: Pharmacist, Pharmacologist, Clinical Researcher, Drug Inspector."},
                {"Public Health", "Protecting and improving community health.\n\nCareers: Epidemiologist, Health Policy Analyst, Biostatistician, Community Health Worker."},
                {"Midwifery", "Care during pregnancy, childbirth, and postpartum.\n\nCareers: Midwife, Maternal Health Consultant, Labor & Delivery Nurse."},
                {"Medical Laboratory Science", "Laboratory analyses for diagnosis and treatment.\n\nCareers: Lab Technologist, Biomedical Scientist, Pathology Technician, Quality Control Analyst."},
                {"Dentistry", "Treatment of diseases of the oral cavity.\n\nCareers: Dentist, Orthodontist, Oral Surgeon, Dental Hygienist."},
                {"Law", "System of rules regulating actions of members.\n\nCareers: Lawyer, Judge, Legal Consultant, Prosecutor, Corporate Counsel."},
                {"Civics & Ethics", "Rights and duties of citizenship and moral principles.\n\nCareers: Ethics Officer, Policy Analyst, Human Rights Advocate, Educator."},
                {"Public Administration & Governance", "Implementation of government policy and public programs.\n\nCareers: Public Administrator, City Manager, Policy Developer, Diplomat."},
                {"Computer Science", "Study of computational systems, software, and hardware.\n\nCareers: Software Developer, Systems Analyst, AI Engineer, Cyber Security Expert."},
                {"Information Science", "Processes for storing and retrieving information.\n\nCareers: Information Architect, Librarian, Database Administrator, Knowledge Manager."},
                {"Information System", "Hardware and software used to collect and distribute data.\n\nCareers: IT Manager, Business Systems Analyst, Network Administrator, ERP Consultant."},
                {"Mathematics", "Study of numbers, quantity, space, and change.\n\nCareers: Mathematician, Actuary, Statistician, Cryptographer, Data Scientist."},
                {"Physics", "Study of matter and behavior through space and time.\n\nCareers: Physicist, Aerospace Engineer, Research Scientist, Nuclear Engineer."},
                {"Chemistry", "Study of matter and the changes it undergoes.\n\nCareers: Chemist, Forensic Scientist, Pharmacologist, Chemical Technician."},
                {"Biology", "Study of living organisms and vital processes.\n\nCareers: Biologist, Microbiologist, Geneticist, Environmental Scientist."},
                {"Statistics", "Science of collecting and interpreting data.\n\nCareers: Statistician, Data Scientist, Risk Analyst, Market Researcher."},
                {"Earth Sciences", "Science dealing with planet Earth and its atmosphere.\n\nCareers: Geologist, Meteorologist, Seismologist, Oceanographer."},
                {"History", "Study of the past through written documents.\n\nCareers: Historian, Archivist, Museum Curator, Researcher, Journalist."},
                {"Geography & Environmental Studies", "Relationships between people and their environments.\n\nCareers: Cartographer, Urban Planner, Environmental Consultant, GIS Specialist."},
                {"Psychology", "Scientific study of the mind and behavior.\n\nCareers: Psychologist, Counselor, HR Specialist, Behavioral Analyst."},
                {"Sociology", "Study of social life, change, and human behavior.\n\nCareers: Sociologist, Social Worker, Policy Analyst, Market Researcher."},
                {"Political Science", "Study of government systems and political behavior.\n\nCareers: Political Analyst, Diplomat, Legislative Assistant, Public Affairs Specialist."},
                {"English Language & Literature", "Study of the English language and literary works.\n\nCareers: Editor, Writer, Journalist, Teacher, Communications Specialist."},
                {"Tigrigna Language & Literature", "Study of the Tigrigna language and its heritage.\n\nCareers: Translator, Linguist, Cultural Heritage Officer, Educator."},
                {"Veterinary Science", "Medicine for prevention and treatment of animal disease.\n\nCareers: Veterinarian, Veterinary Surgeon, Animal Health Consultant."},
                {"Animal Health & Production", "Maintaining animal health and optimizing production.\n\nCareers: Livestock Health Officer, Farm Manager, Animal Nutritionist."},
                {"Civil Engineering", "Design and maintenance of the built environment.\n\nCareers: Civil Engineer, Structural Engineer, Construction Manager, Site Engineer."},
                {"Electrical & Computer Engineering", "Engineering dealing with electricity and electronics.\n\nCareers: Electrical Engineer, Hardware Engineer, Network Engineer, Power Systems Engineer."},
                {"Mechanical Engineering", "Design and manufacture of tools and machines.\n\nCareers: Mechanical Engineer, Automotive Engineer, Robotics Engineer, Manufacturing Engineer."},
                {"Chemical Engineering", "Converting raw materials into products using chemistry.\n\nCareers: Chemical Engineer, Process Engineer, Petroleum Engineer, Safety Engineer."},
                {"Industrial Engineering", "Optimization of complex processes and systems.\n\nCareers: Industrial Engineer, Quality Engineer, Logistics Manager, Systems Integrator."},
                {"Architecture & Urban Planning", "Designing buildings and the built environment.\n\nCareers: Architect, Urban Designer, Planning Consultant, Interior Architect."},
                {"Information Technology", "Systems for storing and sending information.\n\nCareers: IT Consultant, Network Specialist, Cloud Architect, Technical Support Manager."},
                {"Software Engineering", "Application of engineering to software development.\n\nCareers: Software Engineer, Full-stack Developer, QA Engineer, DevOps Specialist."},
                {"Pedagogical Sciences", "Study of the theory and practice of education.\n\nCareers: Education Consultant, School Administrator, Curriculum Developer, Trainer."},
                {"Climate & Society Studies", "Relationship between climate change and human society.\n\nCareers: Climate Policy Analyst, Sustainability Consultant, Environmental Advocate."},
                {"Geo-information & Earth Observation", "Technology to handle geographical data.\n\nCareers: GIS Analyst, Remote Sensing Specialist, Photogrammetrist."},
                {"Environment & Development Studies", "Integration of environmental and social development.\n\nCareers: Development Officer, Environmental Impact Analyst, Sustainability Manager."}
        };

        for (String[] dept : departments) {
            ContentValues values = new ContentValues();
            values.put("name", dept[0]);
            values.put("description", dept[1]);
            db.insert("departments", null, values);
        }
    }
}