local stock = redis.call('GET',KEYS[1])

if stock and tonumber(stock) >= tonumber(ARGV[1]) then
    redis.call('DECRBY',KEYS[1],ARGV[1])
    return 1
else
    return 0
end