package com.food_on.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.food_on.app.R;
import com.food_on.app.ReusableCode.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class ChefRegisteration extends AppCompatActivity {

    String[] Western_Province={"Colombo","Gampaha","Kalutara"};
    String[] Central_Province={"Kandy","Matale","Nuwara Eliya"};
    String[] Southern_Province={"Galle","Matara","Hambantota"};
    String[] Northern_Province={"Jaffna","Kilinochchi","Mannar","Mullaitivu","Vavuniya"};
    String[] Eastern_Province={"Batticaloa","Ampara","Trincomalee"};
    String[] North_Western_Province={"Kurunegala","Puttalam"};
    String[] North_Central_Province={"Anuradhapura","Polonnaruwa"};
    String[] Uva_Province={"Badulla","Monaragala"};
    String[] Sabaragamuwa_Province={"Kegalle","Ratnapura"};




    String[] Nuwara_Eliya = {"Nuwara Eliya", "Hatton", "Nawalapitiya", "Ginigathhena", "Ramboda", "Maskeliya", "Talawakelle", "Bogawantalawa", "Kotagala", "Lindula", "Hapugastalawa", "Thalawakele", "Norton Bridge", "Rozella", "Watawala", "Labukele", "Agarapatana", "Ragala", "Nuwara Eliya", "Gampola", "Ginigathhena", "Wattegama", "Nawalapitiya", "Kundasale", "Pussellawa", "Kotmale", "Talawakele", "Hatton", "Maskeliya", "Ramboda", "Norton Bridge", "Bogawantalawa", "Lindula", "Hapugastalawa", "Thalawakele", "Labukele", "Watawala", "Agarapatana", "Ragala", "Laxapana", "Nallathanniya"};
    String[] Colombo = {"Colombo", "Dehiwala-Mount Lavinia", "Sri Jayawardenepura Kotte", "Moratuwa", "Nugegoda", "Rajagiriya", "Kohuwala", "Maharagama", "Boralesgamuwa", "Kaduwela", "Battaramulla", "Piliyandala", "Wellawatte", "Colpetty (Colombo 03)", "Bambalapitiya (Colombo 04)", "Kollupitiya (Colombo 03)", "Thalawathugoda", "Malabe", "Mount Lavinia", "Ratmalana", "Panadura", "Kalubowila", "Attidiya", "Pelawatte", "Hokandara", "Koswatte", "Nawala", "Mirihana", "Dematagoda", "Kirulapone" };
    String[] Gampaha = {"Gampaha", "Negombo", "Ja-Ela", "Wattala", "Kadawatha", "Kelaniya", "Ganemulla", "Minuwangoda", "Divulapitiya", "Veyangoda", "Katunayake", "Seeduwa", "Athurugiriya", "Mahara", "Nittambuwa", "Weliweriya", "Gampaha", "Kaduwela", "Dompe", "Pugoda", "Mirigama", "Ragama", "Kandana", "Negombo", "Ja-Ela", "Wattala", "Kelaniya", "Biyagama", "Malwana", "Horana", "Ingiriya", "Padukka", "Delgoda", "Homagama"};

    String[] Kalutara = {"Kalutara", "Panadura", "Horana", "Beruwala", "Aluthgama", "Matugama", "Wadduwa", "Bandaragama", "Kalutara", "Kesbewa", "Piliyandala", "Moratuwa", "Mount Lavinia", "Ratmalana", "Dehiwala-Mount Lavinia", "Homagama", "Padukka", "Ingiriya", "Horana", "Bulathsinhala", "Agalawatta", "Matugama", "Baduraliya", "Kahapola", "Beruwala", "Aluthgama", "Dharga Town", "Maggona", "Payagala", "Walallawita", "Wadduwa", "Katukurunda", "Panadura", "Horana", "Kahapola", "Bandaragama", "Pannila", "Walallawita", "Horana", "Bulathsinhala"};

    String[] Kandy = {"Kandy", "Peradeniya", "Gampola", "Katugastota", "Pilimatalawa", "Kundasale", "Digana", "Teldeniya", "Wattegama", "Akurana", "Gelioya", "Mahiyanganaya", "Kegalle", "Mawanella", "Kotmale", "Warakapola", "Galagedara", "Rambukkana", "Matale", "Dambulla", "Sigiriya", "Naula", "Laggala", "Watthegama"};

    String[] Matale = {"Matale", "Dambulla", "Galewela", "Naula", "Pallepola", "Yatawatta", "Ukuwela", "Sigiriya", "Inamaluwa", "Nalanda", "Palapathwala", "Wilgamuwa", "Ududumbara", "Dawalagala", "Laggala", "Rattota", "Ambanganga Kanda", "Doragamuwa", "Elkaduwa", "Gammaduwa", "Hettipola", "Ipalogama", "Kimbissa", "Madawala Bazaar", "Mahaoya", "Morayaya", "Nalanda", "Pitawala", "Rattota", "Udadumbara"};

    String[] Galle = {"Galle", "Ambalangoda", "Hikkaduwa", "Balapitiya", "Boossa", "Unawatuna", "Karapitiya", "Habaraduwa", "Koggala", "Weligama", "Mirissa", "Matara", "Ahangama", "Talpe", "Walahanduwa", "Kathaluwa", "Thalpe", "Baddegama", "Imaduwa", "Elpitiya", "Bentota", "Induruwa", "Kosgoda", "Ahungalla", "Aluthgama", "Beruwala", "Kalutara", "Panadura"};

    String[] Matara = {"Matara", "Weligama", "Kamburugamuwa", "Dikwella", "Dickwella", "Tangalle", "Hakmana", "Akuressa", "Deniyaya", "Kirinda", "Kotapola", "Devinuwara", "Pitabeddara", "Malimbada", "Waluwatta", "Gandara", "Morawaka", "Hiniduma", "Thihagoda", "Pasgoda", "Weligama", "Kamburugamuwa", "Dikwella", "Dickwella", "Tangalle", "Hakmana", "Akuressa", "Deniyaya", "Kirinda", "Kotapola", "Devinuwara", "Pitabeddara", "Malimbada", "Waluwatta", "Gandara", "Morawaka", "Hiniduma", "Thihagoda", "Pasgoda"};

    String[] Hambantota = {"Hambantota", "Tangalle", "Ambalantota", "Tissamaharama", "Suriyawewa", "Weeraketiya", "Ranna", "Beliatta", "Tangalle", "Ambalantota", "Tissamaharama", "Suriyawewa", "Weeraketiya", "Ranna", "Beliatta", "Kirinda", "Walasmulla", "Sooriyawewa"};

    String[] Jaffna = {"Jaffna", "Nallur", "Point Pedro", "Chavakachcheri", "Vaddukoddai", "Mannar", "Kilinochchi", "Pooneryn", "Tellippalai", "Kopay", "Chankanai", "Chavakachcheri", "Karainagar", "Uduvil", "Alaveddy", "Maruthankerney", "Kaithady", "Navanthurai", "Myliddy", "Valvettithurai", "Araliyagaha Gedara", "Sandilipay", "Thondaimanaru", "Manipay", "Atchuvely", "Velanai", "Pandatharippu", "Chunnakam", "Kankesanthurai"};

    String[] Kilinochchi = {"Kilinochchi", "Elephant Pass", "Poonakari", "Karachchi", "Paranthan", "Pachchilaipalli", "Akkarayankulam", "Kandavalai", "Mankulam", "Oddusuddan", "Thunukkai"};

    String[] Mannar = {"Mannar", "Nanaddan", "Madhu", "Talaimannar", "Adampan", "Pesalai", "Musal", "Mannar", "Murunkan", "Vankalai", "Vellankulam"};
    String[] Vavuniya = {"Vavuniya", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Nedunkeni", "Kekirawa", "Nelukkulam", "Omanthai", "Puliyankulama", "Nedunkeni", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Thandikulam", "Mamaduwa", "Nelukkulam", "Omanthai", "Puliyankulama", "Samanthurai", "Nedunkeni", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Thandikulam", "Mamaduwa", "Vairavapuliyankulam", "Padaviya", "Nedunkeni", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Thandikulam", "Mamaduwa", "Vairavapuliyankulam", "Padaviya", "Pudukuduirippu", "Nedunkeni", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Thandikulam", "Mamaduwa", "Vairavapuliyankulam", "Padaviya", "Pudukuduirippu", "Nedunkeni", "Vavuniya South", "Vavuniya North", "Vavuniya Town", "Thandikulam", "Mamaduwa", "Vairavapuliyankulam", "Padaviya", "Pudukuduirippu", "Puliyankulama"};

    String[] Mullaitivu = {"Mullaitivu", "Mankulam", "Oddusuddan", "Puthukudiyiruppu", "Maritimepattu", "Thunukkai", "Mulliyawalai", "Nedunkerni", "Pudukuduirippu", "Welioya", "Puthukudiyiruppu", "Manthai East", "Manthai West", "Thunukkai", "Welioya", "Puthukudiyiruppu"};

    String[] Batticaloa = {"Batticaloa", "Kattankudy", "Valaichchenai", "Chenkalady", "Eravur", "Vakarai", "Kaluvanchikudy", "Manmunai", "Ampilanthurai", "Kokkadicholai", "Porathivupattu", "Paddiruppu", "Kalawanchikudi", "Vantharumoolai", "Thalavai", "Karadiyanaru", "Vavunathivu", "Kaluvankerny", "Karaitivu", "Palameenmadu", "Thalankudah", "Periyapullumalai", "Kokkaddicholai", "Mavadipuram", "Chettipalayam", "Kaluwankerni", "Kattumurivu", "Navatkudah", "Unnichchai", "Vellaveli", "Palchenai", "Kaluwankerni North", "Nintavur", "Kirankulam", "Vavunatheevu", "Thalankudah West", "Kokkadicholai North", "Kokkadicholai West", "Mahiladitivu"};

    String[] Ampara = {"Ampara", "Kalmunai", "Akkaraipattu", "Sainthamaruthu", "Ampara", "Pottuvil", "Kalmunai", "Sammanthurai", "Nintavur", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai", "Kalmunai"};

    String[] Trincomalee = {"Trincomalee", "Kinniya", "Muttur", "Kantalai", "Seruvila", "Thampalakaamam", "Morawewa", "Gomarankadawala", "Kuchchaveli", "Nayaru", "Kuchchaveli", "Nilaveli", "Kuchchaveli", "Kokkaddicholai", "Verugal", "Padaviya", "Horowupotana", "Kantalai", "Kantalai", "Seruvila", "Thampalakaamam", "Morawewa", "Gomarankadawala", "Trincomalee", "Trincomalee", "Trincomalee", "Trincomalee"};

    String[] Kurunegala = {"Kurunegala", "Nikaweratiya", "Kuliyapitiya", "Polgahawela", "Mawathagama", "Alawwa", "Pannala", "Giriulla", "Wariyapola", "Narammala", "Kobeigane", "Galgamuwa", "Kotavehera", "Hiripitiya", "Bingiriya", "Bamunakotuwa", "Rasnayakapura", "Weerambugedara", "Melsiripura", "Narampanawa", "Panduwasnuwara", "Ganewatta", "Giribawa", "Hettipola", "Ibbagamuwa", "Maho", "Maho Junction", "Mallawapitiya", "Makandura", "Pothuhera", "Rideegama", "Udubaddawa", "Wadakada", "Yaggapitiya", "Wariyapola", "Mallawapitiya", "Kuliyapitiya"};

    String[] Puttalam = {"Puttalam", "Chilaw", "Anamaduwa", "Wennappuwa", "Marawila", "Nattandiya", "Kalpitiya", "Madampe", "Dankotuwa", "Mahawewa", "Nawagattegama", "Karukupane", "Mundel", "Eluvankarai", "Palavi", "Nawadamkulama", "Norochcholai", "Wariyapola", "Puttalam", "Kalpitiya", "Marawila", "Chilaw", "Anamaduwa", "Madampe", "Wennappuwa", "Nattandiya", "Dankotuwa", "Mahawewa", "Nawagattegama", "Karukupane", "Eluwankarai", "Mundel", "Palavi", "Nawadamkulama", "Norochcholai", "Wariyapola", "Udappuwa", "Thillayadi", "Vanathavilluwa", "Thoduwawa", "Mundalama", "Pallimunai", "Kochchikade"};

    String[] Anuradhapura = {"Anuradhapura", "Medawachchiya", "Talawa", "Nochchiyagama", "Horowpothana", "Kekirawa", "Kahatagasdigiliya", "Mihintale", "Eppawala", "Rambewa", "Kebithigollewa", "Habarana", "Ritigala", "Thambuttegama", "Galenbindunuwewa", "Mahavilachchiya", "Ipalogama", "Nelumkulama", "Elayapattuwa", "Kebithigollewa", "Nochchiyagama", "Kahatagasdigiliya", "Talawa", "Horowpothana", "Kekirawa", "Medawachchiya", "Tambuttegama", "Rambewa", "Mihintale", "Eppawala", "Habarana", "Ritigala", "Galenbindunuwewa", "Mahavilachchiya", "Ipalogama", "Nelumkulama", "Elayapattuwa", "Kekirawa", "Rambewa", "Horowpothana", "Talawa", "Nochchiyagama", "Medawachchiya", "Anuradhapura"};

    String[] Polonnaruwa = {"Polonnaruwa", "Hingurakgoda", "Medirigiriya", "Kaduruwela", "Bakamuna", "Dimbulagala", "Elahera", "Manampitiya", "Polonnaruwa", "Kaduruwela", "Hingurakgoda", "Medirigiriya", "Dimbulagala", "Bakamuna", "Elahera", "Manampitiya"};

    String[] Badulla = {"Badulla", "Bandarawela", "Haputale", "Welimada", "Ella", "Passara", "Mahiyanganaya", "Monaragala", "Bibile", "Rideegama", "Welimada", "Ella", "Passara", "Hali Ela", "Meegahakivula", "Mahiyanganaya", "Wiyaluwa", "Badalkumbura", "Buttala", "Thanamalvila", "Wellawaya", "Soragune", "Diyathalawa", "Haldummulla", "Beragala", "Koslanda", "Uva-Paranagama"};

    String[] Monaragala = {"Monaragala", "Badalkumbura", "Bibile", "Buttala", "Kataragama", "Siambalanduwa", "Wellawaya", "Medagama", "Madulla", "Madulsima", "Siyambalanduwa", "Buddama", "Sella Kataragama", "Thanamalwila", "Okkampitiya", "Hali Ela", "Keenagahamaditta", "Passara", "Medagama", "Bibila", "Hingurakgoda", "Walawe Hamangoda", "Sewanagala", "Monaragala", "Mellapitiya", "Kolambage Ara", "Potaragoda", "Uva Paranagama", "Galge", "Kumbukkana", "Buttala", "Maligawila", "Kadurugas Ara", "Kuda Oya", "Kuda Oya Town", "Tissamaharama", "Weeraketiya", "Yudaganawa", "Kotagama", "Ethalawatte"};

    String[] Ratnapura = {"Ratnapura", "Embilipitiya", "Balangoda", "Kuruwita", "Eheliyagoda", "Pelmadulla", "Kiriella", "Nivithigala", "Kolonna", "Rakwana", "Kahawatta", "Erewwala", "Godakawela", "Embilipitiya", "Ratnapura", "Balangoda", "Eheliyagoda", "Kuruwita", "Pelmadulla", "Kiriella", "Nivithigala", "Kolonna", "Rakwana", "Kahawatta", "Erewwala", "Godakawela", "Weligepola", "Ayagama", "Kuruvita", "Kalawana", "Opanayaka", "Ambilipitiya", "Eheliyagoda", "Nivithigala", "Kolonna", "Kahawatta", "Kuruwita", "Balangoda", "Rakwana", "Kalawana", "Pelmadulla", "Ratnapura", "Erewwala", "Godakawela", "Kuruvita", "Ayagama", "Ambilipitiya"};

    String[] Kegalle = {"Kegalle", "Mawanella", "Kegalle", "Warakapola", "Rambukkana", "Aranyaka", "Kitulgala", "Dehiowita", "Deraniyagala", "Galigamuwa", "Malkaduwawa", "Bulathkohupitiya", "Yatiyanthota", "Ruwanwella", "Hemmathagama", "Rambuka", "Warakapola", "Polgahawela", "Galigamuwa", "Kegalle", "Mawanella", "Kithulgala", "Dehiowita", "Bulathkohupitiya", "Deraniyagala", "Hemmathagama", "Malkaduwawa", "Yatiyanthota", "Rambukkana", "Kitulgala", "Aranyaka", "Ruwanwella", "Rambuka"};




    TextInputLayout Fname, Lname, Email, Pass, cfpass, mobileno, houseno, area, postcode;
    Spinner statespin, Cityspin, Suburban;
    Button signup, Emaill, Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname;
    String lname;
    String emailid;
    String password;
    String confirmpassword;
    String mobile;
    String house;
    String Area;
    String Postcode;
    String role = "Chef";
    String statee;
    String cityy;
    String suburban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registeration);

        Fname = (TextInputLayout) findViewById(R.id.Firstname);
        Lname = (TextInputLayout) findViewById(R.id.Lastname);
        Email = (TextInputLayout) findViewById(R.id.Email);
        Pass = (TextInputLayout) findViewById(R.id.Pwd);
        cfpass = (TextInputLayout) findViewById(R.id.Cpass);
        mobileno = (TextInputLayout) findViewById(R.id.Mobileno);
        houseno = (TextInputLayout) findViewById(R.id.houseNo);
        area = (TextInputLayout) findViewById(R.id.Area);
        postcode = (TextInputLayout) findViewById(R.id.Postcode);
        statespin = (Spinner) findViewById(R.id.Statee);
        Cityspin = (Spinner) findViewById(R.id.Citys);
        Suburban = (Spinner) findViewById(R.id.Suburban);
        signup = (Button) findViewById(R.id.Signup);
        Emaill = (Button) findViewById(R.id.emaill);
        Phone = (Button) findViewById(R.id.phone);
        Cpp = (CountryCodePicker) findViewById(R.id.CountryCode);


        statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                statee = value.toString().trim();
                if (statee.equals("Western_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Western_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Central_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Central_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Southern_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Southern_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Northern_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Northern_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Eastern_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Eastern_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("North_Western_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : North_Western_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("North_Central_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : North_Central_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Uva_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Uva_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Sabaragamuwa_Province")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Sabaragamuwa_Province) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                cityy = value.toString().trim();
                if (cityy.equals("Nuwara_Eliya")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Nuwara_Eliya) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }

                if (cityy.equals("Colombo")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Colombo) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }

                if (cityy.equals("Gampaha")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Gampaha) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }

                if (cityy.equals("Kalutara")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Kalutara) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Kandy")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Kandy) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Matale")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Matale) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Galle")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Galle) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Matara")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Matara ) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Hambantota")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Hambantota ) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Jaffna")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Jaffna ) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Kilinochchi")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Kilinochchi ) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Mannar")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Mannar) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Vavuniya")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Vavuniya) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Vavuniya")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Vavuniya) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Mullaitivu")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Mullaitivu) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Batticaloa")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Batticaloa) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Ampara")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Ampara) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Trincomalee")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Trincomalee) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Kurunegala")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Kurunegala) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Puttalam")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Puttalam) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Anuradhapura")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Anuradhapura) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Polonnaruwa")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Polonnaruwa) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Badulla")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Badulla) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Monaragala")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Monaragala) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Ratnapura")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Ratnapura) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
                if (cityy.equals("Kegalle")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Kegalle) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Suburban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                suburban = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Chef");
        FAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confirmpassword = cfpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                Postcode = postcode.getEditText().getText().toString().trim();


                if (isValid()) {

                    final ProgressDialog mDialog = new ProgressDialog(ChefRegisteration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registering please wait...");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Role", role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String, String> hashMappp = new HashMap<>();
                                        hashMappp.put("Area", Area);
                                        hashMappp.put("City", cityy);
                                        hashMappp.put("ConfirmPassword", confirmpassword);
                                        hashMappp.put("EmailID", emailid);
                                        hashMappp.put("Fname", fname);
                                        hashMappp.put("House", house);
                                        hashMappp.put("Lname", lname);
                                        hashMappp.put("Mobile", mobile);
                                        hashMappp.put("Password", password);
                                        hashMappp.put("Postcode", Postcode);
                                        hashMappp.put("State", statee);
                                        hashMappp.put("Suburban", suburban);
                                        firebaseDatabase.getInstance().getReference("Chef")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(ChefRegisteration.this);
                                                            builder.setMessage("Registered Successfully,Please Verify your Email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                    String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(ChefRegisteration.this, MainMenu.class);
                                                                    b.putExtra("phonenumber", phonenumber);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog alert = builder.create();
                                                            alert.show();

                                                        } else {
                                                            mDialog.dismiss();
                                                            ReusableCodeForAll.ShowAlert(ChefRegisteration.this, "Error", task.getException().getMessage());

                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });


                            } else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(ChefRegisteration.this, "Error", task.getException().getMessage());
                            }

                        }
                    });

                }

            }

        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChefRegisteration.this, ChefLogin.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(ChefRegisteration.this, Chefloginphone.class);
                startActivity(e);
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cfpass.setErrorEnabled(false);
        cfpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        postcode.setErrorEnabled(false);
        postcode.setError("");

        boolean isValidname = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false, isvalidlname = false, isvalidhousestreetno = false, isvalidarea = false, isvalidpostcode = false;
        if (TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("Firstname is required");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("Lastname is required");
        } else {
            isvalidlname = true;
        }
        if (TextUtils.isEmpty(emailid)) {
            Email.setErrorEnabled(true);
            Email.setError("Email is required");
        } else {
            if (emailid.matches(emailpattern)) {
                isValidemail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                Pass.setErrorEnabled(true);
                Pass.setError("password too weak");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            cfpass.setErrorEnabled(true);
            cfpass.setError("Confirm Password is required");
        } else {
            if (!password.equals(confirmpassword)) {
                Pass.setErrorEnabled(true);
                Pass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(mobile)) {
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile number is required");
        } else {
            if (mobile.length() < 9) {
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid mobile number");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(house)) {
            houseno.setErrorEnabled(true);
            houseno.setError("Field cannot be empty");
        } else {
            isvalidhousestreetno = true;
        }
        if (TextUtils.isEmpty(Area)) {
            area.setErrorEnabled(true);
            area.setError("Field cannot be empty");
        } else {
            isvalidarea = true;
        }
        if (TextUtils.isEmpty(Postcode)) {
            postcode.setErrorEnabled(true);
            postcode.setError("Field cannot be empty");
        } else {
            isvalidpostcode = true;
        }

        isvalid = (isValidname && isvalidpostcode && isvalidlname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isvalidarea && isvalidhousestreetno) ? true : false;
        return isvalid;
    }

    public void onBackPressed() {
        // Navigate back to MainActivity
        Intent intent = new Intent(ChefRegisteration.this, MainMenu.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}



