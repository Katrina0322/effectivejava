namespace java com.effective.hermes.thrift
struct Router{
    1: string ip
    2: string table
    3: string key
}

struct Data{
    1: string columnName
    2: string columnValue
    3: i64 timestamp
    4: byte type
}

service UpdateColumn{
    bool update(1: Router router, 2: Data data)
}