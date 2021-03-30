package com.ridhoafni.core.fake

//class FakeMovieDbRepository(
//        private val remoteDataSource: RemoteDataSource,
//        private val localDataSource: LocalDataSource,
//        private val appExecutors: AppExecutors
//    ) : IMovieDbRepository {
//
////    override fun getDiscoverMovies(): Flow<Resource<List<DataMovie>>> =
////        object : NetworkBoundResource<List<Movie>, List<DataMovie>>(appExecutors) {
////
////            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getPopularMovies().map { DataMapper.mapEntityToDomain(it) }
////
////            override fun shouldFetch(data: List<Movie>?): Boolean =  data == null || data.isEmpty()
////
////            override suspend fun createCall(): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getPopularMovies()
////
////            override suspend fun saveCallResult(data: List<DataMovie>) {
////                val movieList = DataMapper.mapResponsesToEntities(data, "popular")
////                localDataSource.insertMovies(movieList)
////            }
////
////        }.asFlow()
//
////    override fun getMovie(id: Int): Flow<Resource<Movie>> =
////        object :NetworkBoundResource<Movie, DataMovie>(appExecutors) {
////            override fun loadFromDB(): Flow<Movie> = localDataSource.getDetailMovie(id).map { DataMapper.mapEntityToDomain(it) }
////
////            override fun shouldFetch(data: Movie?): Boolean = data == null
////
////            override suspend fun createCall(): Flow<ApiResponse<DataMovie>> = remoteDataSource.getDetailMovie(id)
////
////            override suspend fun saveCallResult(data: DataMovie) {
////                val movieList = DataMapper.mapResponsesToEntities(data)
////                localDataSource.updateMovie(movieList)
////            }
////        }.asFlow()
//
//
//}