package com.ad.service;

import com.ad.model.Ads;

import java.util.List;

public interface DataService {
    public List<Ads> getAllAdds();
    public boolean saveAdds(Ads adds);
    public Ads getAdd();
}
