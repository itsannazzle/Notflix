package com.example.notflix.ui.movies

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentMoviesFragmentBinding
import com.example.notflix.ui.detail.DetailMoviesActivity
import com.example.notflix.ui.util.CAMERA_PERMISSION_CODE
import com.example.notflix.ui.util.LOCATION_PERMISSION_CODE
import com.example.notflix.ui.util.cameraPermission
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.ui.UseableAdapter
import com.nextint.core.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), ActivityCompat.OnRequestPermissionsResultCallback {
    private var _binding: FragmentMoviesFragmentBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private lateinit var adapter : UseableAdapter<MoviesModel>
    private val viewModel : MoviesViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesFragmentBinding.inflate(inflater, container, false)
        root = binding?.root
        cameraPermission(requireContext(),Manifest.permission.CAMERA,requireActivity(),0)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            binding?.progressCircular?.visibility = View.VISIBLE
            viewModel.showTrendingMovies().observe(viewLifecycleOwner,{
                    trending ->
                when(trending){
                    is ResourceData.Success -> {
                        binding?.progressCircular?.visibility = View.GONE
                        adapter.submitList(trending.data)
                        adapter.notifyDataSetChanged()
                        binding?.rvMovies?.adapter = adapter
                    }
                    is ResourceData.Error -> Toast.makeText(activity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    is ResourceData.Loading -> binding?.progressCircular?.visibility = View.VISIBLE
                }
            })
        }
        showTrending()
    }

    private fun showTrending(){
        adapter = UseableAdapter{
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,it.id_movies)
            startActivity(intent)
        }
        with(binding?.rvMovies){
            this?.layoutManager = GridLayoutManager(requireContext(),2)
            this?.setHasFixedSize(true)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    showTrending()
                    Toast.makeText(requireContext(),"Permission granted",Toast.LENGTH_SHORT).show()
                    Log.i("permission",permissions[1])
                } else {
                    Toast.makeText(requireContext(),"Permission NOT granted",Toast.LENGTH_SHORT).show()
                }
            }
            LOCATION_PERMISSION_CODE -> {
                Log.i("permission",permissions[2])
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(),"Permission granted",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(),"Permission NOT granted",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        root = null
    }
}