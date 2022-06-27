package thuc.dvph17759.du_an_1.FRG;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import thuc.dvph17759.du_an_1.Activity.ThongTinKhachHangActivity;
import thuc.dvph17759.du_an_1.R;

public class MapFragment extends Fragment {
    TextView ed_diaChi;
    Button btn_dongy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " KG " + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        googleMap.addMarker(markerOptions);

                        getAddress(getContext(), latLng.latitude, latLng.longitude);

                        ed_diaChi = v.findViewById(R.id.ed_dia_chi);
                        ed_diaChi.setText(getAddress(getContext(), latLng.latitude, latLng.longitude));

                        btn_dongy = v.findViewById(R.id.btn_ok);


                        btn_dongy.setOnClickListener(v -> {
                            Intent intent = new Intent(getContext() , ThongTinKhachHangActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("adress", getAddress(getContext(), latLng.latitude, latLng.longitude));
                            intent.putExtras(bundle);

                            startActivity(intent);
                        });
                    }
                });
            }
        });
        return v;
    }

    public static String getAddress(Context context, double LATITUDE, double LONGITUDE) {

//Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.d("TAG", "getAddress:  address" + address);
                Log.d("TAG", "getAddress:  city" + city);
                Log.d("TAG", "getAddress:  state" + state);
                Log.d("TAG", "getAddress:  postalCode" + postalCode);
                Log.d("TAG", "getAddress:  knownName" + knownName);

                return address + city ;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}