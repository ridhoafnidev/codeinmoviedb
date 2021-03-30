package com.ridhoafni.codeinmoviedb.view.movie

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ridhoafni.core.ui.MovieAdapter
import com.ridhoafni.codeinmoviedb.R
import com.ridhoafni.codeinmoviedb.databinding.MovieFragmentBinding
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.utils.ItemDecorator
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()

    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var chipGroup: ChipGroup
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupToolbar()
        initComponents()
        getGenres()
        getMovies()
    }

    private fun initComponents() {
        movieAdapter = MovieAdapter()
        with(binding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ItemDecorator(resources.getDimensionPixelSize(R.dimen.component_medium), resources.getDimensionPixelSize(R.dimen.component_medium)))
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }


    private fun getGenres() {
        binding.horizontalScrollView.isHorizontalScrollBarEnabled = false
        movieViewModel.getGenres().observe(viewLifecycleOwner, {
            chipGroup = binding.chipGroup
            for (genre in it){
                val chip = layoutInflater.inflate(R.layout.item_chip_filter, chipGroup, false) as Chip
                chip.tag = genre.id.toString()
                chip.text = genre.name
                chipGroup.addView(chip)
            }
            chipGroup.forEach { child ->
                (child as? Chip)?.setOnCheckedChangeListener { _, _ ->
                    registerFilterChanged()
                }
            }
        })
    }

    private fun registerFilterChanged() {
        val ids = chipGroup.checkedChipIds
        val titles = mutableListOf<CharSequence>()

        ids.forEach{ id ->
            titles.add(chipGroup.findViewById<Chip>(id).tag.toString())
        }

        val text = if (titles.isNotEmpty()){
            titles.joinToString(", ")
        }else{
            "No Choice"
        }

        movieViewModel.getMoviesByGenres(text).observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is ApiResponse.Empty -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is ApiResponse.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is ApiResponse.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
    }

    private fun setupToolbar() {
        val toolBar = (activity as AppCompatActivity)
        toolBar.setSupportActionBar(binding.toolbar)
        if (toolBar.getSupportActionBar() != null) {
            toolBar.supportActionBar?.title = getString(R.string.app_name)
        }
    }

    private fun getMovies() {
        movieViewModel.getDiscoverMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is ApiResponse.Empty -> {
                        binding.progressBar.visible()
                        binding.recyclerViewMovie.gone()
                    }
                    is ApiResponse.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerViewMovie.visible()
                        movieAdapter.setData(movies.data)
                    }
                    is ApiResponse.Error -> {
                        binding.progressBar.gone()
                        binding.viewError.tvError.visible()
                    }
                }
            }
        })
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: DataMovie) {
                val toDetailFragment = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(movie)
                findNavController().navigate(toDetailFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}