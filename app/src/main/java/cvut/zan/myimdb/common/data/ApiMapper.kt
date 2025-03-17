package cvut.zan.myimdb.common.data

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity):Domain
    //fun mapFromDomain(apiDto: Domain):Entity
}