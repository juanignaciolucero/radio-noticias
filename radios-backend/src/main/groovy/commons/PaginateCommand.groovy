package commons

import grails.validation.Validateable

class PaginateCommand implements Validateable{
    Integer offset
    String sort
    String order

    Map params() {
        return [
            offset: offset ?: 0,
            sort  : sort,
            order : order,
            max   : 10
        ]
    }
}
