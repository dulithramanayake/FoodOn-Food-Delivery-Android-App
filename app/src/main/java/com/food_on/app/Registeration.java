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
import android.widget.Toast;

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

public class Registeration extends AppCompatActivity {

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



    TextInputLayout fname, lname, localadd, emaill, pass, cmpass, Mobileno;
    Spinner statespin, City, Suburban;
    Button Signin, Email, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String statee;
    String cityy;
    String suburban;
    String email;
    String password;
    String firstname;
    String lastname;
    String Localaddress;
    String confirmpass;
    String mobileno;
    String role = "Customer";
    CountryCodePicker Cpp;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        try {
            mDialog = new ProgressDialog(Registeration.this);
            mDialog.setMessage("Registering please wait...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            fname = (TextInputLayout) findViewById(R.id.Fname);
            lname = (TextInputLayout) findViewById(R.id.Lname);
            localadd = (TextInputLayout) findViewById(R.id.Localaddress);
            emaill = (TextInputLayout) findViewById(R.id.Emailid);
            pass = (TextInputLayout) findViewById(R.id.Password);
            cmpass = (TextInputLayout) findViewById(R.id.confirmpass);
            Signin = (Button) findViewById(R.id.button);
            statespin = (Spinner) findViewById(R.id.Statee);
            City = (Spinner) findViewById(R.id.Citys);
            Suburban = (Spinner) findViewById(R.id.Suburban);
            Mobileno = (TextInputLayout) findViewById(R.id.Mobilenumber);
            Cpp = (CountryCodePicker) findViewById(R.id.CountryCode);
            Email = (Button) findViewById(R.id.emaill);
            Phone = (Button) findViewById(R.id.phone);


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
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Central_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Central_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Southern_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Southern_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Northern_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Northern_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Eastern_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Eastern_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("North_Western_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : North_Western_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("North_Central_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : North_Central_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Uva_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Uva_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Sabaragamuwa_Province")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Sabaragamuwa_Province) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    cityy = value.toString().trim();
                    if (cityy.equals("Nuwara_Eliya")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Nuwara_Eliya) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Colombo")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Colombo) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Gampaha")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Gampaha) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Kalutara")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Kalutara) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Kandy")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Kandy) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Matale")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Matale) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Galle")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Galle) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Matara")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Matara ) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Hambantota")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Hambantota ) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Jaffna")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Jaffna ) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Kilinochchi")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Kilinochchi ) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Mannar")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Mannar) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Vavuniya")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Vavuniya) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Vavuniya")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Vavuniya) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Mullaitivu")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Mullaitivu) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Batticaloa")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Batticaloa) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Ampara")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Ampara) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Trincomalee")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Trincomalee) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Kurunegala")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Kurunegala) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Puttalam")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Puttalam) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Anuradhapura")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Anuradhapura) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Polonnaruwa")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Polonnaruwa) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Badulla")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Badulla) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Monaragala")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Monaragala) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Ratnapura")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Ratnapura) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Kegalle")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Kegalle) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
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


            databaseReference = firebaseDatabase.getInstance().getReference("Customer");
            FAuth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emaill.getEditText().getText().toString().trim();
                    password = pass.getEditText().getText().toString().trim();
                    firstname = fname.getEditText().getText().toString().trim();
                    lastname = lname.getEditText().getText().toString().trim();
                    Localaddress = localadd.getEditText().getText().toString().trim();
                    confirmpass = cmpass.getEditText().getText().toString().trim();
                    mobileno = Mobileno.getEditText().getText().toString().trim();

                    if (isValid()) {

                        mDialog.show();

                        FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                    final HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("Role", role);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            HashMap<String, String> hashMappp = new HashMap<>();
                                            hashMappp.put("City", cityy);
                                            hashMappp.put("ConfirmPassword", confirmpass);
                                            hashMappp.put("EmailID", email);
                                            hashMappp.put("FirstName", firstname);
                                            hashMappp.put("LastName", lastname);
                                            hashMappp.put("Mobileno", mobileno);
                                            hashMappp.put("Password", password);
                                            hashMappp.put("LocalAddress", Localaddress);
                                            hashMappp.put("State", statee);
                                            hashMappp.put("Suburban", suburban);
                                            firebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                mDialog.dismiss();
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Registeration.this);
                                                                builder.setMessage("Registered Successfully,Please Verify your Email");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        dialog.dismiss();
                                                                        String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobileno;
                                                                        Intent b = new Intent(Registeration.this, MainMenu.class);
                                                                        b.putExtra("phonenumber", phonenumber);
                                                                        startActivity(b);

                                                                    }
                                                                });
                                                                AlertDialog alert = builder.create();
                                                                alert.show();

                                                            } else {
                                                                mDialog.dismiss();
                                                                ReusableCodeForAll.ShowAlert(Registeration.this,"Error",task.getException().getMessage());

                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });

                                } else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Registeration.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }


                }
            });
        } catch (Exception e) {
            mDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Registeration.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(Registeration.this, LoginPhone.class);
                startActivity(e);
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        emaill.setErrorEnabled(false);
        emaill.setError("");
        fname.setErrorEnabled(false);
        fname.setError("");
        lname.setErrorEnabled(false);
        lname.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");
        cmpass.setErrorEnabled(false);
        cmpass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");

        boolean isValidfirstname = false, isValidlastname = false, isValidaddress = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false;
        if (TextUtils.isEmpty(firstname)) {
            fname.setErrorEnabled(true);
            fname.setError("FirstName is required");
        } else {
            isValidfirstname = true;
        }
        if (TextUtils.isEmpty(lastname)) {
            lname.setErrorEnabled(true);
            lname.setError("LastName is required");
        } else {
            isValidlastname = true;
        }
        if (TextUtils.isEmpty(email)) {
            emaill.setErrorEnabled(true);
            emaill.setError("Email is required");
        } else {
            if (email.matches(emailpattern)) {
                isValidemail = true;
            } else {
                emaill.setErrorEnabled(true);
                emaill.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(mobileno)) {
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Mobile number is required");
        } else {
            if (mobileno.length() < 9) {
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Invalid mobile number");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(password)) {
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                pass.setErrorEnabled(true);
                pass.setError("Password too weak");
                cmpass.setError("password too weak");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpass)) {
            cmpass.setErrorEnabled(true);
            cmpass.setError("Confirm Password is required");
        } else {
            if (!password.equals(confirmpass)) {
                pass.setErrorEnabled(true);
                pass.setError("Password doesn't match");
                cmpass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(Localaddress)) {
            localadd.setErrorEnabled(true);
            localadd.setError("Local Address is required");
        } else {
            isValidaddress = true;
        }
        isvalid = (isValidfirstname && isValidlastname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isValidaddress) ? true : false;
        return isvalid;
    }
    public void onBackPressed() {
        // Navigate back to MainActivity
        Intent intent = new Intent(Registeration.this, MainMenu.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}

