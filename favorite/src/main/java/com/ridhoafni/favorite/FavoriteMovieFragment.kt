package com.ridhoafni.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridhoafni.codeinmoviedb.R
import com.ridhoafni.codeinmoviedb.databinding.FavoriteMovieFragmentBinding
import com.ridhoafni.codeinmoviedb.di.FavoriteModuleDependencies
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.ui.MovieAdapter
import com.ridhoafni.core.utils.ItemDecorator
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import com.ridhoafni.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        factory
    }

    private var _binding: FavoriteMovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favorite_movie)
        setHasOptionsMenu(true)
//        getFavoriteMovies()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

//    private fun getFavoriteMovies() {
//        val favoriteMovieAdapter = MovieAdapter()
//        favoriteMovieViewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
//            if (movies.isNotEmpty()) {
//                binding.progressBar.gone()
//                binding.recyclerViewFavoriteMovie.visible()
//                movies.let {
//                    favoriteMovieAdapter.setData(it)
//                }
//            }else{
//                binding.progressBar.gone()
//                binding.recyclerViewFavoriteMovie.gone()
//                binding.viewEmpty.contentEmpty.visible()
//            }
//        })
//
//        with(binding.recyclerViewFavoriteMovie) {
//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(ItemDecorator(resources.getDimensionPixelSize(R.dimen.radius),resources.getDimensionPixelSize(R.dimen.radius)))
//            setHasFixedSize(true)
//            adapter = favoriteMovieAdapter
//        }
//
//        favoriteMovieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
//            override fun onItemClicked(movie: Movie) {
//                val toDetailFavoriteMovieFragment =
//                    FavoriteMovieFragmentDirections.actionFavoriteMovieFragmentToDetailMovieFragment(
//                        movie
//                    )
//                findNavController().navigate(toDetailFavoriteMovieFragment)
//            }
//        })
//    }

    override fun onDestroyView() {
        binding.recyclerViewFavoriteMovie.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
            override fun onViewAttachedToWindow(v: View) {}
            override fun onViewDetachedFromWindow(v: View) {
                binding.recyclerViewFavoriteMovie.adapter=null
            }
        })
        binding.recyclerViewFavoriteMovie.adapter = null
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            val toMovieFragment =
                FavoriteMovieFragmentDirections.actionFavoriteMovieFragmentToMovieFragment()
            findNavController().navigate(toMovieFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}