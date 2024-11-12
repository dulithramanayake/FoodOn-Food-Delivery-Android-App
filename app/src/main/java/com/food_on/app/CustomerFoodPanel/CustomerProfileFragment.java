package com.food_on.app.CustomerFoodPanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.food_on.app.Customer;
import com.food_on.app.MainMenu;
import com.food_on.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerProfileFragment extends Fragment {


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



    EditText firstname, lastname, address;
    Spinner State, City, Suburban;
    TextView mobileno, Email;
    Button Update;
    LinearLayout password, LogOut;
    DatabaseReference databaseReference, data;
    FirebaseDatabase firebaseDatabase;
    String statee, cityy, suburban, email, passwordd, confirmpass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");
        View v = inflater.inflate(R.layout.fragment_customerprofile, null);

        firstname = (EditText) v.findViewById(R.id.fnamee);
        lastname = (EditText) v.findViewById(R.id.lnamee);
        address = (EditText) v.findViewById(R.id.address);
        Email = (TextView) v.findViewById(R.id.emailID);
        State = (Spinner) v.findViewById(R.id.statee);
        City = (Spinner) v.findViewById(R.id.cityy);
        Suburban = (Spinner) v.findViewById(R.id.sub);
        mobileno = (TextView) v.findViewById(R.id.mobilenumber);
        Update = (Button) v.findViewById(R.id.update);
        password = (LinearLayout) v.findViewById(R.id.passwordlayout);
        LogOut = (LinearLayout) v.findViewById(R.id.logout_layout);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Customer customer = dataSnapshot.getValue(Customer.class);

                firstname.setText(customer.getFirstName());
                lastname.setText(customer.getLastName());
                address.setText(customer.getLocalAddress());
                mobileno.setText(customer.getMobileno());
                Email.setText(customer.getEmailID());
                State.setSelection(getIndexByString(State, customer.getState()));
                State.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object value = parent.getItemAtPosition(position);
                        statee = value.toString().trim();
                        if (statee.equals("Western_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Western_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Central_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Central_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Southern_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Southern_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Northern_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Northern_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Eastern_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Eastern_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("North_Western_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : North_Western_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("North_Central_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : North_Central_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Uva_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Uva_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Sabaragamuwa_Province")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Sabaragamuwa_Province) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        City.setSelection(getIndexByString(City, customer.getCity()));
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
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }

                        if (cityy.equals("Colombo")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Colombo) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }

                        if (cityy.equals("Gampaha")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Gampaha) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }

                        if (cityy.equals("Kalutara")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Kalutara) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Kandy")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Kandy) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Matale")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Matale) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Galle")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Galle) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Matara")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Matara ) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Hambantota")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Hambantota ) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Jaffna")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Jaffna ) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Kilinochchi")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Kilinochchi ) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Mannar")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Mannar) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Vavuniya")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Vavuniya) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Vavuniya")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Vavuniya) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Mullaitivu")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Mullaitivu) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Batticaloa")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Batticaloa) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Ampara")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Ampara) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Trincomalee")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Trincomalee) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Kurunegala")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Kurunegala) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Puttalam")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Puttalam) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Anuradhapura")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Anuradhapura) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Polonnaruwa")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Polonnaruwa) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Badulla")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Badulla) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Monaragala")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Monaragala) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Ratnapura")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Ratnapura) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Kegalle")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Kegalle) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        Suburban.setSelection(getIndexByString(Suburban, customer.getSuburban()));
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateinformation();
        return v;
    }

    private void updateinformation() {


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                data = FirebaseDatabase.getInstance().getReference("Customer").child(useridd);
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Customer customer = dataSnapshot.getValue(Customer.class);


                        confirmpass = customer.getConfirmPassword();
                        email = customer.getEmailID();
                        passwordd = customer.getPassword();
                        long mobilenoo = Long.parseLong(customer.getMobileno());

                        String Fname = firstname.getText().toString().trim();
                        String Lname = lastname.getText().toString().trim();
                        String Address = address.getText().toString().trim();

                        HashMap<String, String> hashMappp = new HashMap<>();
                        hashMappp.put("City", cityy);
                        hashMappp.put("ConfirmPassword", confirmpass);
                        hashMappp.put("EmailID", email);
                        hashMappp.put("FirstName", Fname);
                        hashMappp.put("LastName", Lname);
                        hashMappp.put("Mobileno", String.valueOf(mobilenoo));
                        hashMappp.put("Password", passwordd);
                        hashMappp.put("LocalAddress", Address);
                        hashMappp.put("State", statee);
                        hashMappp.put("Suburban", suburban);
                        firebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(hashMappp);

                        // After updating the data, show a dialog box
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Use getContext() here
                        builder.setTitle("Data Updated");
                        builder.setMessage("Your data has been updated successfully.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Close the dialog
                                dialog.dismiss();
                            }
                        });

                        // Create and show the dialog
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CustomerPassword.class);
                startActivity(intent);
            }
        });

        mobileno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), CustomerPhonenumber.class);
                startActivity(i);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), MainMenu.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }

    private int getIndexByString(Spinner st, String spist) {
        int index = 0;
        for (int i = 0; i < st.getCount(); i++) {
            if (st.getItemAtPosition(i).toString().equalsIgnoreCase(spist)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
