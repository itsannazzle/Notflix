package com.example.notflix.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentTvShowBinding
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.ui.UseableAdapter
import com.nextint.core.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private lateinit var adapter: UseableAdapter<TvShowModel>
    private val viewModel : TvShowViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding?.progressCircular?.visibility = View.VISIBLE
            viewModel.showTvShow().observe(viewLifecycleOwner, { tvShow ->
                when (tvShow) {
                    is ResourceData.Success -> {
                        binding?.progressCircular?.visibility = View.GONE
                        adapter.submitList(tvShow.data)
                        adapter.notifyDataSetChanged()
                        binding?.rvTvshow?.adapter = adapter
                    }
                    is ResourceData.Loading -> binding?.progressCircular?.visibility = View.VISIBLE
                    is ResourceData.Error -> Toast.makeText(activity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }

            })
        }
        showPopularTvShow()
    }

    private fun showPopularTvShow(){
        adapter = UseableAdapter{
            val intent = Intent(activity, DetailTvShowActivity::class.java)
            intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,it.id_tvshow)
            startActivity(intent)
        }
        with(binding?.rvTvshow){
            this?.layoutManager = GridLayoutManager(requireContext(),2)
            this?.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        root = null
    }
}