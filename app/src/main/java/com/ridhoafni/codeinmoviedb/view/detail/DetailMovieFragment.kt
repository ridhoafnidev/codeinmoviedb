package com.ridhoafni.codeinmoviedb.view.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.chip.Chip
import com.ridhoafni.codeinmoviedb.R
import com.ridhoafni.codeinmoviedb.databinding.DetailMovieFragmentBinding
import com.ridhoafni.codeinmoviedb.view.adapter.ReviewAdapter
import com.ridhoafni.codeinmoviedb.view.adapter.TrailerAdapter
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.data.local.entity.MovieDetails
import com.ridhoafni.core.data.local.entity.ReviewEntity
import com.ridhoafni.core.data.local.entity.TrailerEntity
import com.ridhoafni.core.utils.Constants
import com.ridhoafni.core.utils.UiUtils
import com.ridhoafni.core.utils.gone
import com.ridhoafni.core.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private val movieDetailViewModel: DetailMovieViewModel by viewModels()

    private var _binding: DetailMovieFragmentBinding? = null
    private val binding get() = _binding!!
    private var movieDetail: MovieDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTheme(R.style.AppThemeLight)
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val movie = DetailMovieFragmentArgs.fromBundle(it).data
            getMovie(movie?.id)
        }
        setupToolbar()
    }

    private fun getMovie(id: Long?) {
        movieDetailViewModel.getMovie(id!!).observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Success -> {
                        binding.networkState.progressBar.gone()
                        movieDetail = it.data
                        Glide.with(requireActivity())
                            .load(Constants.IMAGE_URL + movieDetail?.movie?.posterPath)
                            .apply(
                                RequestOptions.placeholderOf(R.color.md_grey_200)
                                    .error(R.drawable.ic_error)
                            )
                            .into(binding.movieDetailsInfo.imagePoster)
                        Glide.with(requireActivity())
                            .load(Constants.IMAGE_URL + movieDetail?.movie?.backdropPath)
                            .apply(
                                RequestOptions.placeholderOf(R.color.md_grey_200)
                                    .error(R.drawable.ic_error)
                            )
                            .into(binding.imageMovieBackdrop)
                        with(binding.movieDetailsInfo) {
                            textTitle.text = movieDetail?.movie?.title
                            textOverview.text = movieDetail?.movie?.overview
                            textReleaseDate.text = movieDetail?.movie?.releaseDate
                            textLanguage.text = movieDetail?.movie?.originalLanguage
                            textReleaseDate.text = movieDetail?.movie?.releaseDate
                            textVote.text = movieDetail?.movie?.voteAverage.toString()
                            labelVote.text = "${movieDetail?.movie?.voteCount.toString()} votes"
                        }
                        val genres = movieDetail?.movie?.genres
                        val chipGroup = binding.movieDetailsInfo.chipGroup
                        for (genre in genres!!) {
                            val chip = Chip(context)
                            chip.text = genre.name
                            chip.chipStrokeWidth = UiUtils.dipToPixels(context, 1.toFloat())
                            chip.chipStrokeColor = ColorStateList.valueOf(
                                activity?.resources!!.getColor(R.color.md_grey_200)
                            )
                            chip.setChipBackgroundColorResource(android.R.color.transparent)
                            chipGroup.addView(chip)
                        }
                        setupTrailersAdapter(movieDetail?.trailers)
                        setupReviewsAdapter(movieDetail?.reviews)

                    }
                    is Resource.Loading -> {
                        binding.networkState.progressBar.visible()
                    }
                    is Resource.Error -> {
                        binding.networkState.errorMsg.visible()
                        binding.networkState.errorMsg.text = "Network error!"
                    }
                }
            }
        }
    }

    private fun setupTrailersAdapter(trailers: List<TrailerEntity>?) {
        val trailerAdapter = TrailerAdapter()
        trailerAdapter.setData(trailers)
        with(binding.movieDetailsInfo.listTrailers) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = trailerAdapter
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    private fun setupReviewsAdapter(reviews: List<ReviewEntity>?) {
        val reviewAdapter = ReviewAdapter()
        reviewAdapter.setData(reviews)
        with(binding.movieDetailsInfo.listReviews) {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = binding.toolbar
        val toolBar = (activity as AppCompatActivity)
        toolBar.setSupportActionBar(toolbar)
        if (toolBar.getSupportActionBar() != null) {
            toolBar.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            handleCollapsedToolbarTitle()
        }
    }

    private fun handleCollapsedToolbarTitle() {
        binding.appbar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.collapsingToolbar.title = movieDetail?.movie?.title
                    isShow = true
                } else if (isShow) {
                    binding.collapsingToolbar.title = ""
                    isShow = false
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){

            val toMovieFragment = DetailMovieFragmentDirections.actionDetailMovieFragmentToMovieFragment()
            findNavController().navigate(toMovieFragment)

        }else if(item.itemId == R.id.action_share){

            activity?.let {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(it)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.title_share))
                    .setText(resources.getString(R.string.share_text, movieDetail?.movie?.title))
                    .startChooser()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}