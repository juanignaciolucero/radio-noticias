package commons

import grails.validation.Validateable

class PaginateCommand implements Validateable {
    Integer offset
    Integer max
    String sort
    String order

    Map params() {
        return [
                max   : max ?: 10,
                offset: offset ?: 0,
                sort  : sort,
                order : order
        ]
    }
}
