package id.capstone.hijoe.abstraction

interface BaseMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity) : DomainModel

    fun mapToEntity(domainModel: DomainModel) : Entity
}